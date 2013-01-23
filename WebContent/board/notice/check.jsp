<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Notice, org.ahsan.swpark.dao.NoticeDao" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	// 글번호가 없으면 목록으로 복귀
	if(request.getParameter("no") == null) {
		response.sendRedirect("list.jsp");
		return;
	}
	
	// 파라메터 읽기
	int no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("pno"));
	String job = request.getParameter("job");
	
	String message = "";
	if(request.getParameter("result") != null && request.getParameter("result").equals("fail")) {
		message = "암호가 일치하지 않습니다.";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 확인</title>
<link rel="stylesheet" href="../../css/notice.css">
<script type="text/javascript">
	function submit() {
		var result = true;
		if(document.fm.password.value == null || document.fm.password.value == "") {
			document.fm.password.setAttribute("class", "error");
			result = false;
		} else {
			document.fm.password.setAttribute("class", "");
		}
		
		if(result) {
			document.fm.submit();
		}
	}
</script>
</head>
<body>
<div id="content">
	<h3>비밀번호 확인</h3>
	<form action="checkpost.jsp" method="post" name="fm">
		<input type="hidden" name="no" value=<%= no %> />
		<input type="hidden" name="pno" value=<%= pno %> />
		<input type="hidden" name="job" value=<%= job %> />
		<table>
			<tr>
				<td class="header" width="60" align="center">비밀번호</td>
				<td>
					<input type="password" name="password" />&nbsp;&nbsp;
					<span id="error" class="error_text"><%= message %></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<span class="button">
						<a href="javaScript:submit()" >확인</a>
					</span>&nbsp;&nbsp;
					<span class="button">
						<a href="list.jsp" >취소</a>
					</span>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>