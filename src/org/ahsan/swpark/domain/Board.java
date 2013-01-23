package org.ahsan.swpark.domain;

import java.sql.Date;

/*
create table board
(no int primary key auto_increment,
 writer varchar(30),
 password varchar(30),
 title varchar(100),
 content varchar(8000),
 wdate date,
 read_cnt int,
 reply_cnt int,
 group_no int,
 sequence_in_group int,
 indent_in_group int,
 ref_no int);
 
create index board_group_no_idx on board(group_no);
*/
public class Board {

	private int no;
	private String writer;
	private String password;
	private String title;
	private String content;
	private Date wdate;
	private int read_cnt;
	private int reply_cnt;
	private int group_no;
	private int sequence_in_group;
	private int indent_in_group;
	private int ref_no;
	
	public Board() {}
	public Board(int no, String writer, String password, String title,
			String content, Date wdate, int read_cnt, int reply_cnt,
			int group_no, int sequence_in_group, int indent_in_group, int ref_no) {
		super();
		this.no = no;
		this.writer = writer;
		this.password = password;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.read_cnt = read_cnt;
		this.reply_cnt = reply_cnt;
		this.group_no = group_no;
		this.sequence_in_group = sequence_in_group;
		this.indent_in_group = indent_in_group;
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
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}
	public int getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public int getSequence_in_group() {
		return sequence_in_group;
	}
	public void setSequence_in_group(int sequence_in_group) {
		this.sequence_in_group = sequence_in_group;
	}
	public int getIndent_in_group() {
		return indent_in_group;
	}
	public void setIndent_in_group(int indent_in_group) {
		this.indent_in_group = indent_in_group;
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
		builder.append("Board [no=");
		builder.append(no);
		builder.append(", writer=");
		builder.append(writer);
		builder.append(", password=");
		builder.append(password);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", wdate=");
		builder.append(wdate);
		builder.append(", read_cnt=");
		builder.append(read_cnt);
		builder.append(", reply_cnt=");
		builder.append(reply_cnt);
		builder.append(", group_no=");
		builder.append(group_no);
		builder.append(", sequence_in_group=");
		builder.append(sequence_in_group);
		builder.append(", indent_in_group=");
		builder.append(indent_in_group);
		builder.append(", ref_no=");
		builder.append(ref_no);
		builder.append("]");
		return builder.toString();
	}
	
}
