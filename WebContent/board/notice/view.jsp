<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="org.ahsan.swpark.dao.NoticeDao, org.ahsan.swpark.domain.Notice" %>
<%
	if(request.getParameter("no") == null) {
		response.sendRedirect("list.jsp");
		return;
	} 
	
	int	no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("pno"));
	
	NoticeDao noticeDao = new NoticeDao();
	Notice notice = noticeDao.getNotice(no);
	noticeDao.addRef(no);
	noticeDao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글보기</title>
<link rel="stylesheet" href="../../css/notice.css" >
</head>
<body>
	<h3>글보기</h3>
	<div id="content">
		
		<table>
			<tr>
				<td class="header" width="60">작성자</td>
				<td><%= notice.getWriter() %></td>
			</tr>
			<tr>
				<td class="header">제목</td>
				<td><%= notice.getTitle() %></td>
			</tr>
			<tr>
				<td class="header" height="400">내용</td>
				<td valign="top"><%= notice.getNotice().replace("\r\n", "<br />") %></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<span class="button"><a href="list.jsp?pno=<%= pno %>">목록</a></span>&nbsp;&nbsp;
					<span class="button"><a href="check.jsp?no=<%= no %>&pno=<%= pno %>&job=edit">편집</a></span>&nbsp;&nbsp;
					<span class="button"><a href="check.jsp?no=<%= no %>&pno=<%= pno %>&job=remove">삭제</a></span>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>