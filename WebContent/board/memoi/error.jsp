<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오류</title>
<link rel="stylesheet" type="text/css" href="../../css/memoi.css">
</head>
<body>
	<div id="content">
		<h3>오류 발생</h3>
		<table>
			<tr>
				<th>오류
				</th>
			</tr>
			<tr>
				<td>예상치 못한 오류가 발생하였습니다.</td>
			</tr>
		</table>
		<div align="center">
			<input type="button" onclick="window.location.href='MyIndex'" value="확 인" />
		</div>
	</div>
</body>
</html>