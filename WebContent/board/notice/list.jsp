<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="org.ahsan.swpark.dao.NoticeDao, org.ahsan.swpark.domain.Notice, java.util.*" %>
<%
	int pno = 1;
	if(request.getParameter("pno") != null) {
		pno = Integer.parseInt(request.getParameter("pno"));
	}
	
	NoticeDao noticeDao = new NoticeDao();
	List<Notice> notices = noticeDao.getTitleByPage(pno);
	
	int total = (int)Math.ceil(noticeDao.getTotalNotice() / (double)NoticeDao.NOTICE_PER_PAGE);
	
	String comment = String.format("전체 %d 페이지 중에 %d 페이지", total, pno);
	noticeDao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 게시판</title>
<link rel="stylesheet" href="../../css/notice.css" >
</head>
<body>
	<div id="content">
		<h3>공지사항</h3>
		<div align="right"><%= comment %></div>
		<table>
			<thead>
				<tr>
					<th width="50">글 번호</th>
					<th>제목</th>
					<th width="50">작성자</th>
					<th width="100">작성일</th>
					<th width="50">조회수</th>
				</tr>
			</thead>
			<tbody>
				<% for(int i=0; i<notices.size(); i++) { %>
				<tr>
					<td align="center"><%= notices.get(i).getNo() %></td>
					<td>
						<a href="view.jsp?no=<%= notices.get(i).getNo() %>&pno=<%= pno %>">
							<%= notices.get(i).getTitle() %>
						</a>
					</td>
					<td align="center"><%= notices.get(i).getWriter() %></td>
					<td align="center"><%= notices.get(i).getWdate() %></td>
					<td align="center"><%= notices.get(i).getRef() %></td>
				</tr>
				<% } %>
				<tr>
					<td colspan="5" align="center">
						<a href="list.jsp?pno=1">&lt;&lt;첫 페이지</a>&nbsp;&nbsp;&nbsp;
						<% if(pno > 1) { %>
							<a href="list.jsp?pno=<%= pno-1 %>">&lt;이전 페이지</a>&nbsp;&nbsp;&nbsp;
						<% } %>
						<% if(pno < total) { %>
							<a href="list.jsp?pno=<%= pno+1 %>">다음 페이지&gt;</a>&nbsp;&nbsp;&nbsp;
						<% } %>
							<a href="list.jsp?pno=<%= total %>">마지막 페이지&gt;&gt;</a>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<span class="button">
							<a href="write.jsp">새 글 쓰기</a>
						</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>