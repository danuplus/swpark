package org.ahsan.swpark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ahsan.swpark.domain.Inquire;

public class InquireDao {
	
	private Connection conn;
	private static final String URL = "jdbc:mysql://localhost:3306/madvirusdb?useUnicode=true&characterEncoding=utf-8";
	String dbUser = "madvirus";
	String pass = "madvirus";
		
	public static final int POST_PER_PAGE = 15;
	
	public InquireDao() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL,dbUser,pass);
	}
	
	public void close() throws SQLException {
		if(conn != null)
			conn.close();
	}
	
	public void addPost(Inquire post) throws SQLException {
		String sql = "insert into bd_inquire(no, title, content, writer, password, wdate, ref, group_no, sequence_in_group, indent_in_group, ref_no) " +
					 "values(0, ?, ?, ?, ?, now(), 0, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, post.getTitle());
		pstmt.setString(2, post.getContent());
		pstmt.setString(3, post.getWriter());
		pstmt.setString(4, post.getPassword());
		pstmt.setInt(5, post.getGroup_no());
		pstmt.setInt(6, post.getSequence_in_group());
		pstmt.setInt(7, post.getIndent_in_group());
		pstmt.setInt(8, post.getRef_no());
		
		pstmt.execute();
	}
	
	public void updateGroupNo() throws SQLException {
		String sql = "update bd_inquire set group_no = no where group_no = 0";
				
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	}
	
	public List<Inquire> getPostByPage(int page) throws SQLException {
		String sql = "select no, title, writer, wdate, ref, indent_in_group, ref_no from bd_inquire order by group_no desc, sequence_in_group asc limit ?, ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, POST_PER_PAGE * (page - 1));
		pstmt.setInt(2, POST_PER_PAGE);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Inquire> posts = new ArrayList<Inquire>();
		
		while(rs.next()) {
			Inquire post = new Inquire();
			post.setNo(rs.getInt("no"));
			post.setTitle(rs.getString("title"));
			post.setWriter(rs.getString("writer"));
			post.setWdate(rs.getDate("wdate"));
			post.setRef(rs.getInt("ref"));
			post.setIndent_in_group(rs.getInt("indent_in_group"));
			post.setRef_no(rs.getInt("ref_no"));
			
			posts.add(post);
		}
		
		rs.close();
		
		return posts;
	}
	
	public int getTotalPost() throws SQLException {
		
		String sql = "select count(no) from bd_inquire";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.next();
		int result = rs.getInt(1);
		
		rs.close();
		return result;
	}
	
	public Inquire getPost(int no) throws SQLException {
		String sql = "select * from bd_inquire where no=?";
		
		Inquire post = null;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			post = new Inquire();
			post.setNo(rs.getInt("no"));
			post.setWriter(rs.getString("writer"));
			post.setPassword(rs.getString("password"));
			post.setWdate(rs.getDate("wdate"));
			post.setTitle(rs.getString("title"));
			post.setContent(rs.getString("content"));
			post.setRef(rs.getInt("ref"));
			post.setGroup_no(rs.getInt("group_no"));
			post.setSequence_in_group(rs.getInt("sequence_in_group"));
			post.setIndent_in_group(rs.getInt("indent_in_group"));
			post.setRef_no(rs.getInt("ref_no"));
		}
		
		rs.close();
		
		return post;
	}
	
	public void addRef(int no) throws SQLException {
		String sql = "update bd_inquire set ref=ref+1 where no=?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public void replyPost(int no, Inquire post) throws SQLException {
		
		Inquire parent = getPost(no); // 부모 글
		
		int parent_group_no = parent.getGroup_no();
		int parent_sequence = parent.getSequence_in_group();
		int parent_indent = parent.getIndent_in_group();
		
		String sql = "update bd_inquire set sequence_in_group = sequence_in_group + 1 " +
					 "where group_no=? and sequence_in_group > ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, parent_group_no);
		pstmt.setInt(2, parent_sequence);
		pstmt.executeUpdate();
		
		post.setGroup_no(parent_group_no);
		post.setSequence_in_group(parent_sequence + 1);
		post.setIndent_in_group(parent_indent + 1);
		post.setRef_no(no);
		
		addPost(post);
	}
	
	public boolean checkPassword(int no, String password) throws SQLException {
		
		String sql = "select count(no) from bd_inquire where no=? and password=?";
				
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
	
	public void removePost(int no) throws SQLException {
		// 해당 글을 삭제하고, 자식글의 참조 번호를 -1로 설정한다.
		String sql = "delete from bd_inquire where no=?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
		
		sql = "update bd_inquire set ref_no=-1 where ref_no=?";
				
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public void editPost(Inquire post) throws SQLException {
		
		String sql = "update bd_inquire set writer=?, password=?, title=?, content=? where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, post.getWriter());
		pstmt.setString(2, post.getPassword());
		pstmt.setString(3, post.getTitle());
		pstmt.setString(4, post.getContent());
		pstmt.setInt(5, post.getNo());
		
		pstmt.executeUpdate();
	}
}
