package org.ahsan.swpark.domain;

import java.sql.Date;

/*
create database madvirus

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
public class Post {
	
	private int no;
	private String title;
	private String content;
	private String writer;
	private String password;
	private int ref;
	private int reply;
	private Date wdate;
	
	public Post() {}
	
	public Post(int no, String title, String content, String writer, String password, int ref, int reply, Date wdate) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.ref = ref;
		this.reply = reply;
		this.wdate = wdate;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	
}
