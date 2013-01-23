package org.ahsan.swpark.domain;

import java.sql.Date;

/*
create table board_reply
(no int primary key auto_increment,
 writer varchar(30),
 password varchar(30),
 memo varchar(1000),
 wdate date,
 ref_no int);
*/
public class BoardReply {
	private int no;
	private String writer;
	private String password;
	private String memo;
	private Date wdate;
	private int ref_no;
	
	public BoardReply() {}
	public BoardReply(int no, String writer, String password, String memo,
			Date wdate, int ref_no) {
		super();
		this.no = no;
		this.writer = writer;
		this.password = password;
		this.memo = memo;
		this.wdate = wdate;
		this.ref_no = ref_no;
	}
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardReply [no=");
		builder.append(no);
		builder.append(", writer=");
		builder.append(writer);
		builder.append(", password=");
		builder.append(password);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", wdate=");
		builder.append(wdate);
		builder.append(", ref_no=");
		builder.append(ref_no);
		builder.append("]");
		return builder.toString();
	}
}
