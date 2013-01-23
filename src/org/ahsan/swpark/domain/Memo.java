package org.ahsan.swpark.domain;

import java.sql.Date;

/*
create table memo
(no int primary key auto_increment,
 memo varchar(4000),
 writer varchar(100),
 password varchar(100),
 date datetime);
*/
public class Memo {
	
	private int no;
	private String memo;
	private String writer;
	private String password;
	private Date date;
	
	public Memo() {}
	
	public Memo(String memo, String writer, String password) {
		super();
		this.memo = memo;
		this.writer = writer;
		this.password = password;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memo [no=");
		builder.append(no);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", writer=");
		builder.append(writer);
		builder.append(", password=");
		builder.append(password);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
	
}
