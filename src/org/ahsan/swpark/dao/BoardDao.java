package org.ahsan.swpark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ahsan.swpark.domain.Board;
import org.ahsan.swpark.domain.BoardReply;

public class BoardDao {

	public static final int BOARD_PER_PAGE = 15;
	private Connection conn;
	private static final String URL = "jdbc:mysql://localhost:3306/madvirusdb?useUnicode=true&characterEncoding=utf-8";
	String dbUser = "madvirus";
	String pass = "madvirus";
	
	public BoardDao() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL,dbUser,pass);
	}
	
	public int getTotalPage() throws SQLException {
		String sql = "SELECT count(no) FROM board";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		
		int total = (int)Math.ceil(rs.getInt(1) / (double)BOARD_PER_PAGE);
		rs.close();
		
		return total;
	}
	
	public List<Board> getBoardByPage(int page) throws SQLException {
		
		String sql = "select no, title, reply_cnt, read_cnt, writer, wdate, indent_in_group, ref_no from board " + 
				 "order by group_no desc, sequence_in_group asc limit ?, ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, (page-1)*BOARD_PER_PAGE);
		pstmt.setInt(2, BOARD_PER_PAGE);
		
		ResultSet rs = pstmt.executeQuery();
		List<Board> boards = new ArrayList<Board>();
		
		while(rs.next()) {
			Board board = new Board();
			board.setNo(rs.getInt("no"));
			board.setTitle(rs.getString("title"));
			board.setReply_cnt(rs.getInt("reply_cnt"));
			board.setRead_cnt(rs.getInt("read_cnt"));
			board.setWriter(rs.getString("writer"));
			board.setWdate(rs.getDate("wdate"));
			board.setIndent_in_group(rs.getInt("indent_in_group"));
			board.setRef_no(rs.getInt("ref_no"));
			
			boards.add(board);
		}
		rs.close();
		return boards;
	}
	
	public void addBoard(Board board) throws SQLException {
		// 새 게시물을 추가.
		String sql = "insert into board(no, writer, password, title, content, " + 
					 "wdate, read_cnt, reply_cnt, group_no, sequence_in_group, indent_in_group, ref_no) " +
					 "values(0, ?, ?, ?, ?, now(), 0, 0, 0, 0, 0, 0)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board.getWriter());
		pstmt.setString(2, board.getPassword());
		pstmt.setString(3, board.getTitle());
		pstmt.setString(4, board.getContent());
		
		pstmt.executeUpdate();
		
		// group_no를 no로 업데이트한다.
		sql = "update board set group_no=no where group_no=0";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	}
	
	public void editBoard(Board board) throws SQLException {
		// 새 게시물을 추가.
		String sql = "update board set writer=?, password=?, title=?, content=? where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board.getWriter());
		pstmt.setString(2, board.getPassword());
		pstmt.setString(3, board.getTitle());
		pstmt.setString(4, board.getContent());
		pstmt.setInt(5, board.getNo());
		
		pstmt.executeUpdate();
	}
	
	public void addChildBoard(int no, Board child) throws SQLException {
		// no 번째 글에 답글 달기
		Board parent = getBoard(no);
		
		// 부모 글과 동일한 그룹 번호를 가진 게시물 중에서 부모 글의 시퀀스 번호보다 큰 게시물의 시퀀스 번호를 1씩 증가한다.
		String sql = "update board set sequence_in_group=sequence_in_group+1 " + 
					 "where group_no=? and sequence_in_group>?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, parent.getGroup_no());
		pstmt.setInt(2, parent.getSequence_in_group());
		
		pstmt.executeUpdate();
		
		// 자식글은 부모 글의 시궠스 번호를 1 증가하여 저장
		sql = "insert into board(no, writer, password, title, content, " + 
				"wdate, read_cnt, reply_cnt, group_no, sequence_in_group, indent_in_group, ref_no) " +
				"values(0, ?, ?, ?, ?, now(), 0, 0, ?, ?, ?, ?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, child.getWriter());
		pstmt.setString(2, child.getPassword());
		pstmt.setString(3, child.getTitle());
		pstmt.setString(4, child.getContent());
		pstmt.setInt(5, parent.getGroup_no());
		pstmt.setInt(6, parent.getSequence_in_group()+1);
		pstmt.setInt(7, parent.getIndent_in_group()+1);
		pstmt.setInt(8, no);
	
		pstmt.executeUpdate();
	}
	
	public Board getBoard(int no) throws SQLException {
		String sql = "SELECT * FROM board where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		Board board = new Board();
		if(rs.next()) {
			board.setNo(rs.getInt("no"));
			board.setWriter(rs.getString("writer"));
			board.setPassword(rs.getString("password"));
			board.setTitle(rs.getString("title"));
			board.setContent(rs.getString("content"));
			board.setWdate(rs.getDate("wdate"));
			board.setRead_cnt(rs.getInt("read_cnt"));
			board.setReply_cnt(rs.getInt("reply_cnt"));
			board.setGroup_no(rs.getInt("group_no"));
			board.setSequence_in_group(rs.getInt("sequence_in_group"));
			board.setIndent_in_group(rs.getInt("indent_in_group"));
			board.setRef_no(rs.getInt("ref_no"));
		}
		rs.close();
		return board;
	}
	
	public boolean checkPassword(int no, String password) throws SQLException {
		String sql = "SELECT count(no) FROM board WHERE no=? and password=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		int result = rs.getInt(1);
		rs.close();
		
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void removeBoard(int no) throws SQLException {
		
		// 해당 글을 삭제
		String sql = "delete from board where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
		
		// 해당 글의 댓글들을 삭제
		sql = "delete from board_reply where ref_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
		
		// 해당 글이 삭제되었으므로 자식 글들에 원글 참조 번호를 -1로 변경
		sql = "update board set ref_no=-1 where ref_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public void addReadCount(int no) throws SQLException {
		String sql = "update board set read_cnt=read_cnt+1 where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public void addReply(BoardReply reply) throws SQLException {
		String sql = "insert into board_reply(no, writer, password, memo, wdate, ref_no) " + 
				 "values(0, ?, ?, ?, now(), ?)";
	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, reply.getWriter());
		pstmt.setString(2, reply.getPassword());
		pstmt.setString(3, reply.getMemo());
		pstmt.setInt(4, reply.getRef_no());
		
		pstmt.executeUpdate();
		
		// 덧글 카운트 증가
		addReplyCount(reply.getRef_no(), 1);
	}
	
	public List<BoardReply> getReplyByNo(int no) throws SQLException {
		String sql = "select * from board_reply where ref_no=? order by no asc";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		List<BoardReply> replies = new ArrayList<BoardReply>();
		
		while(rs.next()) {
			BoardReply reply = new BoardReply();
			reply.setNo(rs.getInt("no"));
			reply.setWriter(rs.getString("writer"));
			reply.setPassword(rs.getString("password"));
			reply.setMemo(rs.getString("memo"));
			reply.setWdate(rs.getDate("wdate"));
			reply.setRef_no(rs.getInt("ref_no"));
			
			replies.add(reply);
		}
		
		rs.close();
		return replies;
		
	}
	
	private void addReplyCount(int no, int cnt) throws SQLException {
		String sql = "update board set reply_cnt=reply_cnt+? where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cnt);
		pstmt.setInt(2, no);
		pstmt.executeUpdate();
	}
	
	public boolean checkReplyPassword(int no, String password) throws SQLException {
		String sql = "SELECT count(no) FROM board_reply WHERE no=? and password=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		int result = rs.getInt(1);
		rs.close();
		
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeReply(int no) throws SQLException {
		// 해당 덧글의 참조 글 번호를 구한다.
		String sql = "select ref_no from board_reply where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		int ref_no = rs.getInt(1);
		rs.close();
		
		// 해당 글의 덧글 갯수를 차감한다.
		addReplyCount(ref_no, -1);
		
		// 덧글 삭제
		sql = "DELETE from board_reply where no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public void close() throws SQLException {
		if(conn!=null) conn.close();
	}
}
