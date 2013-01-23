package org.ahsan.swpark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ahsan.swpark.domain.Notice;

public class NoticeDao {

	private static final String URL = "jdbc:mysql://localhost:3306/madvirusdb?useUnicode=true&characterEncoding=utf-8";
	String dbUser = "madvirus";
	String pass = "madvirus";
	
	public static final int NOTICE_PER_PAGE = 15;
	private Connection mConnection;
	
	public NoticeDao() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		mConnection = DriverManager.getConnection(URL, dbUser, pass);
	}
	
	public void close() throws SQLException {
		if(mConnection!=null) {
			mConnection.close();
		}
	}
	
	public List<Notice> getAllTitle() throws SQLException {
		
		String sql = "SELECT no, title, writer, wdate, ref FROM bd_notice ORDER BY no DESC";
		Statement stmt = mConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		List<Notice> notices = new ArrayList<Notice>();
		while(rs.next()) {
			Notice notice = new Notice();
			notice.setNo(rs.getInt("no"));
			notice.setTitle(rs.getString("title"));
			notice.setWriter(rs.getString("writer"));
			notice.setWdate(rs.getDate("wdate"));
			notice.setRef(rs.getInt("ref"));
			
			notices.add(notice);
		}
		rs.close();
		return notices;
	}
	
	public List<Notice> getTitleByPage(int page) throws SQLException {
		
		String sql = "SELECT no, title, writer, wdate, ref FROM bd_notice ORDER BY no DESC limit ?,?";
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		
		pstmt.setInt(1, (page - 1) * NOTICE_PER_PAGE);
		pstmt.setInt(2, NOTICE_PER_PAGE);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Notice> notices = new ArrayList<Notice>();
		while(rs.next()) {
			Notice notice = new Notice();
			notice.setNo(rs.getInt("no"));
			notice.setTitle(rs.getString("title"));
			notice.setWriter(rs.getString("writer"));
			notice.setWdate(rs.getDate("wdate"));
			notice.setRef(rs.getInt("ref"));
			
			notices.add(notice);
		}
		rs.close();
		return notices;
	}
	
	public int getTotalNotice() throws SQLException {
		
		String sql = "SELECT count(*) FROM bd_notice";
		Statement stmt = mConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		
		int result = rs.getInt(1);
		rs.close();
		
		return result;
	}
	
	public void addNotice(Notice notice) throws SQLException {
		String sql = "INSERT INTO bd_notice(no, title, notice, writer, password, wdate, ref) " +
					"values(0, ?, ?, ?, ?, now(), 0)";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		
		pstmt.setString(1, notice.getTitle());
		pstmt.setString(2, notice.getNotice());
		pstmt.setString(3, notice.getWriter());
		pstmt.setString(4, notice.getPassword());
		
		pstmt.executeUpdate();
	}
	
	public void editNotice(Notice notice) throws SQLException {
		
		String sql = "UPDATE bd_notice SET title=?, notice=?, writer=?, password=? where no=?";
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
	
		pstmt.setString(1, notice.getTitle());
		pstmt.setString(2, notice.getNotice());
		pstmt.setString(3, notice.getWriter());
		pstmt.setString(4, notice.getPassword());
		pstmt.setInt(5, notice.getNo());
	
		pstmt.executeUpdate();
	}
	
	public void removeNotice(int no) throws SQLException {
		
		String sql = "DELETE FROM bd_notice where no=?";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public Notice getNotice(int no) throws SQLException {
		
		String sql = "SELECT no, title, notice, writer, password, wdate, ref FROM bd_notice where no=?";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		
		Notice notice = new Notice();
		
		if(rs.next()) {
			notice.setNo(rs.getInt("no"));
			notice.setTitle(rs.getString("title"));
			notice.setNotice(rs.getString("notice"));
			notice.setWriter(rs.getString("writer"));
			notice.setPassword(rs.getString("password"));
			notice.setWdate(rs.getDate("wdate"));
			notice.setRef(rs.getInt("ref"));
		}
		
		rs.close();
		return notice;
	}
	
	public void addRef(int no) throws SQLException {
		String sql = "UPDATE bd_notice set ref=ref+1 where no=?";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public boolean checkPassword(int no, String password) throws SQLException {
		
		String sql = "SELECT count(*) from bd_notice where no=? and password=?";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		
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
}
