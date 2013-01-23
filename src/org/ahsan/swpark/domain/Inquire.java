package org.ahsan.swpark.domain;

import java.sql.Date;

/*
성격 : 문의 게시판
이름 : inquire

create table bd_inquire
(no int primary key auto_increment,	-- 기본키
 title varchar(100),				-- 제목
 content varchar(4000),				-- 내용
 writer varchar(20),				-- 작성자
 password varchar(20),				-- 암호
 wdate datetime,					--  작성일
 ref int,							-- 조회수
 group_no int,						-- 원글과 답글의 그룹번호, 원글과 답글은 원글의 글 번호를 그룹번호로 저장
 sequence_in_group int,				-- 해당 그룹 내에셔 답글의 순서
 indent_in_group int,				-- 답글의 들여쓰기 정도
 ref_no int);						-- 원글인 경우 0, 답글의 경우 원글의 글 번호가 저장, 원글이 삭제되면 -1을 저장
*/

public class Inquire {
	private int no;
	private String title;
	private String content;
	private String writer;
	private String password;
	private Date wdate;
	private int ref;
	private int group_no;
	private int sequence_in_group;
	private int indent_in_group;
	private int ref_no;
	
	public Inquire() {}
	
	public Inquire(int no, String title, String content, String writer, String password, Date wdate,
			int ref, int group_no, int sequence_in_group, int indent_in_group, int ref_no) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.wdate = wdate;
		this.ref = ref;
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
	
}
