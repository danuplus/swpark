package org.ahsan.swpark.domain;

import java.sql.Date;
/*
create table bd_board
(no int primary key auto_increment,	-- 기본키
 title varchar(100),	-- 제목
 notice varchar(4000),	-- 내용
 writer varchar(20),	-- 작성자
 password varchar(20),	-- 암호
 wdate datetime,		-- 작성일
 ref int);				-- 조회수
*/
public class Notice {

	private int no;
	private String title;
	private String notice;
	private String writer;
	private String password;
	private Date wdate;
	private int ref;
	
	public Notice() {}
	
	public Notice(int no, String title, String notice, String writer, String password, Date wdate, int ref) {
		super();
		this.no = no;
		this.title = title;
		this.notice = notice;
		this.writer = writer;
		this.password = password;
		this.wdate = wdate;
		this.ref = ref;
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
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
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
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
}
