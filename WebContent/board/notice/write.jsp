<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="../../css/notice.css" >
<script type="text/javascript">
	function submit() {
		var result = true;
		if(checkNull(document.fm.writer)) result = false;
		if(checkNull(document.fm.password)) result = false;;
		if(checkNull(document.fm.title)) result = false;
		if(checkNull(document.fm.notice)) result = false;
		
		if(result) {
			document.fm.submit();
		}
	}
	
	function checkNull(object) {
		if(object.value == null || object.value == "") {
			object.setAttribute("class", "error");
			return true;
		} else {
			object.setAttribute("class", "");
			return false;
		}
	}
</script>
</head>
<body>
<div id="content">
	<h3>글쓰기</h3>
	<form action="writepost.jsp" method="post" name="fm">
		<table>
			<tr>
				<td width="60" class="header">작성자</td>
				<td><input type="text" name="writer" /></td>
			</tr>
			<tr>
				<td class="header">비밀번호</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td class="header">제목</td>
				<td><input type="text" size="100" name="title" /></td>
			</tr>
			<tr>
				<td class="header">내용</td>
				<td><textarea rows="20" cols="80" name="notice" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<span class="button"><a href="javascript:submit()">확인</a></span>	&nbsp;&nbsp;
					<span class="button"><a href="list.jsp">취소</a></span>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>