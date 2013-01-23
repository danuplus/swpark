<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="../../css/post.css" >
<script type="text/javascript">
	function submit() {
		var result = true;
		
		if(checkNull(document.fm.writer, document.getElementById("error_writer"), "작성자를 입력하세요" )) result = false;
		if(checkNull(document.fm.password, document.getElementById("error_password"), "암호를 입력하세요")) result = false;
		if(checkNull(document.fm.title, document.getElementById("error_title"), "제목을 입력하세요")) result = false;
		
		if(result) {
			document.fm.submit();
		}
	}
	
	function checkNull(src, target, message) {
		if(src.value == null || src.value == "") {
			target.innerHTML = message;
			src.setAttribute("class", "error_box");
			return true;
		} else {
			target.innerHTML = "";
			src.setAttribute("class", "");
			return false;
		}
	}
</script>
</head>
<body>
	<div id="content">
		<h3>글쓰기</h3>
		<form action="writepost.jsp" name="fm" method="post">
			<table>
				<tr>
					<th width="60">작성자</th>
					<td>
						<input type="text" name="writer" />
						<span class="error" id="error_writer"></span>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="password" />
						<span class="error" id="error_password"></span>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="title" size="80" />
						<span class="error" id="error_title"></span>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="25" cols="80" name="content"></textarea>
					</td>
				</tr>
			</table><br />
			<div align="center">
				<span class="button">
					<a href="javascript:submit()">확 인</a>
				</span>&nbsp;&nbsp;&nbsp;
				<span class="button">
					<a href="list.jsp">취 소</a>
				</span>
			</div>
		</form>
	</div>
</body>
</html>