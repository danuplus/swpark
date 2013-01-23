package org.ahsan.swpark.domain;

import java.sql.Date;

/*
create table post_reply
(no int primary key auto_increment,
 writer varchar(20),
 password varchar(20),
 memo varchar(200),
 wdate date,
 ref_no int);
*/
public class PostReply {

	private int no;
	private String writer;
	private String password;
	private String memo;
	private Date wdate;
	private int ref_no;
	
	public PostReply() {}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getRef_no() {
		return ref_no;
	}
	public void setRef_no(int ref_no) {
		this.ref_no = ref_no;
	}
	
}
