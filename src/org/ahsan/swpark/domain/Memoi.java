package org.ahsan.swpark.domain;

import java.util.Date;

public class Memoi {

	private int no;
	private String memo;
	private String writer;
	private String password;
	private Date date;
	
	public Memoi() {}
	
	public Memoi(int no, String memo, String writer, String password, Date date) {
		super();
		this.no = no;
		this.memo = memo;
		this.writer = writer;
		this.password = password;
		this.date = date;
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
		builder.append("memoi [no=");
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
