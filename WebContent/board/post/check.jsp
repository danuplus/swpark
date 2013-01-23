<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%
	if(request.getParameter("no")==null || request.getParameter("page")==null ||
		request.getParameter("job")==null) {
		response.sendRedirect("list.jsp");
		return;
	} 
	
	String job = request.getParameter("job");
	
	if(job.equals("remove_reply") && request.getParameter("rno")==null) {
		response.sendRedirect("list.jsp");
		return;
	}
	
	String result = "";
	if(request.getParameter("result")!=null && request.getParameter("result").equals("fail")) {
		result = "암호가 일치하지 않습니다.";
	}
	
	String queryString = request.getQueryString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 확인</title>
<link rel="stylesheet" href="../../css/post.css" >
<script type="text/javascript">
	function submit() {
		if(document.fm.password.value=="" || document.fm.password.value==null) {
			document.fm.password.setAttribute("class", "error_box");
			document.getElementById("error_password").innerHTML = "암호를 입력해주세요";
		} else {
			document.fm.password.setAttribute("class", "");
			document.getElementById("error_password").innerHTML = "";
			document.fm.submit();
		}
	}
</script>
</head>
<body>
<div id="content">
	<h3>비밀번호 확인</h3>
	<form action="checkpost.jsp?<%= queryString %>" method="post" name="fm">
		<table>
			<tr>
				<th width="60">암호</th>
				<td>
					<input type="password" name="password" />
					<span class="error" id="error_password"><%= result %></span>
				</td>
			</tr>
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