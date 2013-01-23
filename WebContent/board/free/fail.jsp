<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao" %>
<%
	if(request.getParameter("no")==null || request.getParameter("page")==null) {
		response.sendRedirect("list.jsp");
		return;
	}

	String queryString = request.getQueryString();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>암호 확인 실패</title>
<link rel="stylesheet" href="../../css/board.css" >
</head>
<body>
	<div id="dialog">
		<table>
			<tr>
				<th>암호 확인</th>
			</tr>
			<tr>
				<td align="center">암호가 일치하지 않습니다.</td>
			</tr>
		</table>
		<br />
		<div align="center">
			<span class="button">
				<a href="view.jsp?<%= queryString %>">확 인</a>
			</span>
		</div>
	</div>
</body>
</html>