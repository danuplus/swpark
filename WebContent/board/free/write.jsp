<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새글 쓰기</title>
<link rel="stylesheet" href="../../css/board.css" >
<script type="text/javascript" src="../../script/util.js"></script>
<script type="text/javascript">
	function submit() {
		
		var result = true;
		
		if(checkNull(document.fm.writer, document.getElementById("error_writer"), "작성자를 입력해주세요")) result = false;
		
		if(checkNull(document.fm.password, document.getElementById("error_password"), "암호를 입력해주세요")) {
			result = false;
		} 
		else if(!isEqual(document.fm.password, document.fm.password2, 
					document.getElementById("error_password2"), "암호가 일치하지 않습니다")) {
			result = false;
		} 

		if(checkNull(document.fm.title, document.getElementById("error_title"), "제목을 입력해주세요")) result = false;
		
		if(result) {
			document.fm.submit();
		}
	}
</script>
</head>
<body>
<div id="content">
	<h3>새글 쓰기</h3>
	<form action="writepost.jsp" name="fm" method="post">
		<table>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="writer" />
					<span class="error" id="error_writer"></span>
				</td>
			</tr>
			<tr>
				<th>암 호</th>
				<td>
					<input type="password" name="password" />
					<span class="error" id="error_password"></span>
				</td>
			</tr>
			<tr>
				<th>암호확인</th>
				<td>
					<input type="password" name="password2" />
					<span class="error" id="error_password2"></span>
				</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td>
					<input type="text" name="title" size="80" />
					<span class="error" id="error_title"></span>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea rows="20" cols="80" name="content"></textarea></td>
			</tr>
		</table><br />
		<div align="center">
			<span class="button"><a href="javascript:submit()">확 인</a></span>&nbsp;&nbsp;
			<span class="button"><a href="list.jsp">취 소</a></span>
		</div>
	</form>
</div>
</body>
</html>