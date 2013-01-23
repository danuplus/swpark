package org.ahsan.swpark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ahsan.swpark.domain.Post;
import org.ahsan.swpark.domain.PostReply;

/*
create table post
(no int primary key auto_increment,
 title varchar(200),
 content varchar(8000),
 writer varchar(20),
 password varchar(20),
 ref int,
 reply int,
 wdate datetime);
*/
public class PostDao {

	public static final int POST_PER_PAGE = 15;
	public static final int POST = 0;
	public static final int REPLY = 1;
	
	private Connection conn;
	private static final String URL = "jdbc:mysql://localhost:3306/madvirusdb?useUnicode=true&characterEncoding=utf-8";
	String dbUser = "madvirus";
	String pass = "madvirus";
	
	public PostDao() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL,dbUser,pass);
	}
	
	public void addPost(Post post) throws SQLException {
		String sql = "insert into post(no, title, content, writer, password, ref, reply, wdate) " +
				 "values(0, ?, ?, ?, ?, 0, 0, now())";
	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, post.getTitle());
		pstmt.setString(2, post.getContent());
		pstmt.setString(3, post.getWriter());
		pstmt.setString(4, post.getPassword());
		
		pstmt.executeUpdate();
	}
	
	public List<Post> getPostByPage(int page) throws SQLException {
		String sql = "select no, title, writer, ref, reply, wdate from post order by no desc limit ?, ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, (page-1)*POST_PER_PAGE);
		pstmt.setInt(2, POST_PER_PAGE);
		
		List<Post> posts = new ArrayList<Post>();
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Post post = new Post();
			post.setNo(rs.getInt("no"));
			post.setTitle(rs.getString("title"));
			post.setWriter(rs.getString("writer"));
			post.setRef(rs.getInt("ref"));
			post.setReply(rs.getInt("reply"));
			post.setWdate(rs.getDate("wdate"));
			
			posts.add(post);
		}
		
		rs.close();
		return posts;
	}
	
	public int getTotalPost() throws SQLException {
		String sql = "select count(no) from post";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		
		int result = rs.getInt(1);
		rs.close();
		
		return result;
	}
	
	public Post getPost(int no) throws SQLException {
		String sql = "select * from post where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		
		Post post = null;
		if(rs.next()) {
			post = new Post();
			post.setNo(rs.getInt("no"));
			post.setTitle(rs.getString("title"));
			post.setContent(rs.getString("content"));
			post.setWriter(rs.getString("writer"));
			post.setPassword(rs.getString("password"));
			post.setWdate(rs.getDate("wdate"));
			post.setRef(rs.getInt("ref"));
			post.setReply(rs.getInt("reply"));
		}
		
		rs.close();
		return post;
	}

	public void addRef(int no) throws SQLException {
		String sql = "UPDATE post set ref=ref+1 WHERE no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	/*
	create table post_reply
	(no int primary key auto_increment,
 	writer varchar(20),
 	password varchar(20),
 	memo varchar(200),
 	wdate date,
 	ref_no int);
	*/	
	public List<PostReply> getAllReply(int no) throws SQLException {
		String sql = "select * from post_reply where ref_no=? order by no asc";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		ResultSet rs = pstmt.executeQuery();
		List<PostReply> replys = new ArrayList<PostReply>();
		while(rs.next()) {
			PostReply reply = new PostReply();
			reply.setNo(rs.getInt("no"));
			reply.setWriter(rs.getString("writer"));
			reply.setPassword(rs.getString("password"));
			reply.setMemo(rs.getString("memo"));
			reply.setWdate(rs.getDate("wdate"));
			reply.setRef_no(rs.getInt("ref_no"));
			
			replys.add(reply);
		}
		
		rs.close();
		return replys;
	}
	
	public void addReply(PostReply reply) throws SQLException {
		String sql = "insert into post_reply(no, writer, password, memo, wdate, ref_no) " +
					 "values(0, ?, ?, ?, now(), ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, reply.getWriter());
		pstmt.setString(2, reply.getPassword());
		pstmt.setString(3, reply.getMemo());
		pstmt.setInt(4, reply.getRef_no());
		
		pstmt.executeUpdate();
	}
	
	public void addReplyCount(int no) throws SQLException {
		String sql = "update post set reply=reply+1 where no=?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		pstmt.executeUpdate();
	}
	
	public void subReplyCount(int no) throws SQLException {
		String sql = "update post set reply=reply-1 where no=?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		pstmt.executeUpdate();
	}
	
	public boolean checkPassword(int no, String password, int type) throws SQLException {
		String sql = "";
		
		switch(type) {
		case POST :
			sql = "select count(no) from post where no=? and password=?";
			break;
		case REPLY :
			sql = "select count(no) from post_reply where no=? and password=?";
			break;
		}
		
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
		String sql = "delete from post where no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		pstmt.executeUpdate();

		sql = "delete from post_reply where ref_no=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}
	
	public void removeReply(int no) throws SQLException {
		String sql = "DELETE FROM post_reply where no=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, no);
		stmt.executeUpdate();
	}
	
	public void editPost(Post post) throws SQLException {
		String sql = "UPDATE post set writer=?, password=?, title=?, content=? where no=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, post.getWriter());
		pstmt.setString(2, post.getPassword());
		pstmt.setString(3, post.getTitle());
		pstmt.setString(4, post.getContent());
		pstmt.setInt(5, post.getNo());
		
		pstmt.executeUpdate();
	}
	
	public void close() throws SQLException {
		if(conn != null) conn.close();
	}
}
