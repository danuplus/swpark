<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>암호 확인 실패</title>
<link rel="stylesheet" type="text/css" href="css/memoi.css">
</head>
<body>
	<div id="content">
		<table>
			<tr>
				<th>암호 확인 결과</th>
			</tr>
			<tr>
				<td>암호가 일치하지 않습니다</td>
			</tr>
		</table>
		<br />
		<div align="center">
			<input type="button" onclick="location.href='MyIndex?page=${param.page}'" value="확 인" />
		</div>
	</div>
</body>
</html>