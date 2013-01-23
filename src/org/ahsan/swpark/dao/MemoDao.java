package org.ahsan.swpark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ahsan.swpark.domain.Memo;

public class MemoDao {

	private static final String URL = "jdbc:mysql://118.130.116.75:3306/madvirusdb?useUnicode=true&characterEncoding=utf-8";
	String dbUser = "gnu";
	String pass = "#2323#";
	
	public static final int MEMO_PER_PAGE = 15;
	private Connection mConnection;
	
	public MemoDao() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		mConnection = DriverManager.getConnection(URL, dbUser, pass);
	}
	
	public List<Memo> getAllMemos() throws SQLException {
		
		String sql = "SELECT * FROM memo ORDER BY no DESC";
		
		Statement stmt = mConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		List<Memo> memos = new ArrayList<Memo>();
		
		while(rs.next()) {
			Memo memo = new Memo();
			memo.setNo(rs.getInt("no"));
			memo.setMemo(rs.getString("memo"));
			memo.setDate(rs.getDate("date"));
			memo.setWriter(rs.getString("writer"));
			memo.setPassword(rs.getString("password"));
			memos.add(memo);
		}
		rs.close();
		return memos;
	}
	
	public List<Memo> getMemoByPage(int page) throws SQLException {
		
		String sql = "SELECT * FROM memo ORDER BY no DESC LIMIT ?, ?";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		pstmt.setInt(1, (page - 1) * MEMO_PER_PAGE);
		pstmt.setInt(2, MEMO_PER_PAGE);
		ResultSet rs = pstmt.executeQuery();
		
		List<Memo> memos = new ArrayList<Memo>();
		
		while(rs.next()) {
			Memo memo = new Memo();
			memo.setNo(rs.getInt("no"));
			memo.setMemo(rs.getString("memo"));
			memo.setDate(rs.getDate("date"));
			memo.setWriter(rs.getString("writer"));
			memo.setPassword(rs.getString("password"));
			memos.add(memo);
		}
		rs.close();
		return memos;
	}
	
	public int getTotalMemo() throws SQLException {
		int total = 0;
		String sql = "SELECT count(*) from memo";
		
		Statement stmt = mConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.next();
		total = rs.getInt(1);
		
		rs.close();
		return total;
	}
	
	public int getTotalPage() throws SQLException {
		int total = getTotalMemo();
		int totalPage = (int)Math.ceil(total / (double)MEMO_PER_PAGE);
		
		return totalPage;
	}
	
	public void addMemo(Memo memo) throws SQLException {
		
		String sql = "INSERT INTO memo(no, memo, writer, password, date) values(0, ?,?,?, now())";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		pstmt.setString(1, memo.getMemo());
		pstmt.setString(2, memo.getWriter());
		pstmt.setString(3, memo.getPassword());
		
		pstmt.executeUpdate();
	}
	
	public boolean checkPassword(int no, String password) throws SQLException {
		
		String sql = "SELECT count(*) cnt FROM memo WHERE no = ? and password = ?";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		if(rs.getInt(1) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeMemo(int no) throws SQLException {
		
		String sql = "DELETE FROM memo WHERE no = ?";
		
		PreparedStatement pstmt = mConnection.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public void close() throws SQLException {
		if(mConnection != null) {
			mConnection.close();
		}
	}
}
