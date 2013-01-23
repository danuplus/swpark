<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="org.ahsan.swpark.dao.InquireDao, org.ahsan.swpark.domain.Inquire, java.util.*" %>
<% 
	int pno = 1;
	if(request.getParameter("page") != null) {
		pno = Integer.parseInt(request.getParameter("page"));
	}
	
	InquireDao dao = new InquireDao();
	List<Inquire> posts = dao.getPostByPage(pno); 
			
	int total = (int)Math.ceil(dao.getTotalPost() / (double)InquireDao.POST_PER_PAGE);
	String status = String.format("전체 %d 페이지 중에 현재 %d 페이지", total, pno);
	dao.close();
%>
<%!
	private String indent(int count) {
		StringBuilder sb = new StringBuilder();
		if(count > 0) {
			for(int i=0; i<count; i++) {
				sb.append("&nbsp;&nbsp;&nbsp;");
			}
			sb.append("<img src='../../images/reply_icon.gif' />");
		}
		return sb.toString();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글목록</title>
<link rel="stylesheet" href="../../css/inquire.css" >
</head>
<body>
	<div id="content">
		<h3>글목록</h3>
		<div align="right"><%= status %></div>
		<table>
			<thead>
				<tr>
					<th width="50">번호</th>
					<th>제목</th>
					<th width="60">작성자</th>
					<th width="60">조회수</th>
					<th width="80">작성일</th>
				</tr>
			</thead>
			<tbody>
				<% for(int i=0; i<posts.size(); i++) { %>
				<tr>
					<td align="center"><%= posts.get(i).getNo() %></td>
					<td align="left">
						<a href="View.jsp?no=<%= posts.get(i).getNo() %>&page=<%= pno %>">
							<%= indent(posts.get(i).getIndent_in_group()) %>
							<% if(posts.get(i).getRef_no()==-1) { %>
								<span class="error">[원글이 삭제된 답글]</span>
							<% } %>
							<%= posts.get(i).getTitle() %>
						</a>
					</td>
					<td align="center"><%= posts.get(i).getWriter() %></td>
					<td align="center"><%= posts.get(i).getRef() %></td>
					<td align="center"><%= posts.get(i).getWdate() %></td>
				</tr>
				<% } %>
				<tr>
					<td colspan="5" align="center">
						<% if(pno != 1 && total > 0) { %>
							<a href="list.jsp?page=1">&lt;&lt;첫 페이지</a>&nbsp;&nbsp;
						<% } %>
						<% if(pno > 1 && total > 0) { %>
							<a href="list.jsp?page=<%= pno-1 %>">&lt;이전 페이지</a>&nbsp;&nbsp;
						<% } %>
						<% if(pno < total && total > 0) { %>
							<a href="list.jsp?page=<%= pno+1 %>">다음 페이지&gt;</a>&nbsp;&nbsp;
						<% } %>
						<% if(pno != total && total > 0) { %>
							<a href="list.jsp?page=<%= total %>">마지막 페이지&gt;&gt;</a>
						<% } %>
					</td>
				</tr>
			</tbody>
		</table>
		<div align="center">
			<span class="button">
				<a href="write.jsp">새 글 쓰기</a>
			</span>
		</div>
	</div>
</body>
</html>