<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>암호 확인</title>
<link rel="stylesheet" href="../css/memo2.css">
<script type="text/javascript">
function submit() {
	if(document.fm.password.value=="" || document.fm.password.value==null) {
		document.fm.password.setAttribute("class", "error-border");
		document.getElementById("error_password").innerHTML = "암호를 입력해주십시요.";
	} else {
		document.fm.setAttribute("class", "");
		document.getElementById("error_password").innerHTML = "";
		document.fm.submit();
	}
}
</script>
</head>
<body>
	<div id="content">
		<h3>암호 확인</h3>
		<form name="fm" action="/swpark/MemoDelete" method="post">
		<input type="hidden" name="no" value="${param.no }" />
		<input type="hidden" name="page" value="${param.page }" />
		<table>
			<tr>
				<th width="60">암호</th>
				<td><input type="password" name="password" />
					<span class="error-text" id="error_password"></span>
			</td>
		</table><br />
		<div align="center">
			<span class="button">
				<a href="javascript:submit()">확 인</a>
			</span>
		</div>
		</form>
	</div>
</body>
</html>