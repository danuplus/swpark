<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Post, org.ahsan.swpark.domain.PostReply" %>
<%@ page import="org.ahsan.swpark.dao.PostDao, java.util.*" %>
<%
	int pno = 1;
	if(request.getParameter("page")!=null){
		pno = Integer.parseInt(request.getParameter("page"));
	}
	
	PostDao dao = new PostDao();
	List<Post> posts = dao.getPostByPage(pno);
	int total = (int)Math.ceil(dao.getTotalPost() / (double)PostDao.POST_PER_PAGE);
	
	String status = String.format("전체 %d 페이지 중에 현재 %d 페이지", total, pno);
	dao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글목록</title>
<link rel="stylesheet" href="../../css/post.css" >
</head>
<body>
<div id="content">
	<h3>글목록</h3>
	<div align="right">
		<span><%= status %></span>
	</div>
	<table>
		<thead>
			<tr>
				<th width="60">글번호</th>
				<th>제목</th>
				<th width="60">작성자</th>
				<th width="40">조회수</th>
				<th width="80">작성일</th>
			</tr>
		</thead>
		<tbody>
			<% for(int i=0; i<posts.size(); i++) { %>
			<tr>
				<td align="center"><%= posts.get(i).getNo() %></td>
				<td>
				<a href="view.jsp?no=<%= posts.get(i).getNo() %>&page=<%= pno %>">
					<%= posts.get(i).getTitle() %>&nbsp;
					<% if(posts.get(i).getReply() > 0) { %>
						<span class="small"><%= "(" + posts.get(i).getReply() + ")" %></span>
					<% } %>
				</a>
				</td>
				<td align="center"><%= posts.get(i).getWriter() %></td>
				<td align="center"><%= posts.get(i).getRef() %></td>
				<td align="center"><%= posts.get(i).getWdate() %></td>
			</tr>
			<% } %>
			<tr align="center">
				<td colspan="5">
					<% if(pno > 1 && total > 0) { %>
						<a href="list.jsp?page=1">&lt;&lt;첫 페이지</a>&nbsp;&nbsp;&nbsp;
					<% } %>
					<% if(pno > 2 && total > 0) { %>
						<a href="list.jsp?page=<%= pno-1 %>">&lt;이전 페이지</a>&nbsp;&nbsp;&nbsp;
					<% } %>
					<% if(pno < total-1 && total > 0) { %>
						<a href="list.jsp?page=<%= pno+1 %>">다음 페이지&gt;</a>&nbsp;&nbsp;&nbsp;
					<% } %>
					<% if(pno < total && total > 0) { %>
						<a href="list.jsp?page=<%= total %>">마지막 페이지&gt;&gt;</a>
					<% } %>
				</td>
			</tr>
		</tbody>
	</table><br />
	<div align="center">
		<span class="button"><a href="write.jsp">새글 쓰기</a></span>
	</div>
</div>
</body>
</html>