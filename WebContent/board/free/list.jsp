<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao, java.util.*" %>
<%
	int pno = 1;

	if(request.getParameter("page")!=null)
		pno = Integer.parseInt(request.getParameter("page"));

	BoardDao dao = new BoardDao();
	List<Board> boards = dao.getBoardByPage(pno);
	int total = dao.getTotalPage();
	String status = String.format("전체 %d 페이지 중에 현재 %d 페이지", total, pno);
	dao.close();
%>
<%!
private String getIndent(int cnt) {
	StringBuilder sb = new StringBuilder();
	
	if(cnt==0)
		return sb.toString();
	
	for(int i=0; i<cnt; i++) {
		sb.append("&nbsp;&nbsp;");
	}
	
	sb.append("<img src='../../images/reply_icon.gif' /> ");
	return sb.toString();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자유게시판</title>
<link rel="stylesheet" href="../../css/board.css" >
</head>
<body>
<div id="content">
	<h3>자유게시판</h3>
	<div align="right"><%= status %></div>
	<table>
		<tr>
			<th width="50">글번호</th>
			<th>제목</th>
			<th width="50">작성자</th>
			<th width="50">조회수</th>
			<th width="80">작성일</th>
		</tr>
		<% for(Board board : boards) { %>
		<tr>
			<td align="center"><%= board.getNo() %></td>
			<td>
				<a href="view.jsp?no=<%= board.getNo() %>&page=<%= pno %>">
					<%= getIndent(board.getIndent_in_group()) %>
					<% if(board.getRef_no() == -1) { %>
						<span class="error">[원글이 삭제된 답글]</span>
					<% } %>				
					<%= board.getTitle() %>
					<% if(board.getReply_cnt() > 0) { %>
						<span class="small"><%= "(" + board.getReply_cnt() + ")" %></span>
					<% } %>
				</a>
			</td>
			<td align="center"><%= board.getWriter() %></td>
			<td align="center"><%= board.getRead_cnt() %></td>
			<td align="center"><%= board.getWdate() %></td>
		</tr>
		<% } %>
		<tr>
			<td colspan="5" align="center">
				<% if(pno > 1) { %>
					<a href="list.jsp?page=1">&lt;&lt;첫 페이지</a>
				<% } %>
				<% if(pno > 2) { %>
					<a href="list.jsp?page=<%= pno-1 %>">&lt;이전 페이지</a>
				<% } %>
				<% if(pno < total-1) { %>
					<a href="list.jsp?page=<%= pno+1 %>">다음 페이지&gt;</a>
				<% } %>
				<% if(pno < total) { %>
					<a href="list.jsp?page=<%= total %>">마지막 페이지&gt;&gt;</a>
				<% } %>
			</td>
		</tr>
	</table><br />
	<div align="center">
		<span class="button"><a href="write.jsp">새글 쓰기</a></span>
	</div>
</div>
</body>
</html>