<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%
	if(request.getParameter("no")==null || request.getParameter("page")==null
		|| request.getParameter("rno")==null) {
		response.sendRedirect("list.jsp");
		return;
	}

	int no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("page"));
	int rno = Integer.parseInt(request.getParameter("rno"));
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 확인</title>
<link rel="stylesheet" href="../../css/board.css">
<script type="text/javascript">
	function submit() {
		if(document.fm.password.value==null || document.fm.password.value=="") {
			document.fm.password.setAttribute("class", "error_box");
			document.getElementById("error_password").innerHTML = "암호를 입력해주세요.";
		} else {
			document.fm.password.setAttribute("class", "");
			document.getElementById("error_password").innerHTML = "";
			document.fm.submit();
		}
	}
</script>
</head>
<body>
	<div id="dialog" >
		<h3>비밀번호 확인</h3>
		<form name="fm" action="removereply.jsp" method="post">
			<input type="hidden" name="no" value="<%= no %>" />
			<input type="hidden" name="page" value="<%= pno %>" />
			<input type="hidden" name="rno" value="<%= rno %>" />
			<table>
				<tr>
					<th width="60">비밀번호</th>
					<td>
						<input type="password" name="password" />
					</td>
				</tr>
			</table>
			<br />
			<div align="center">
				<span class="button">
					<a href="javascript:submit()">확인</a>
				</span>&nbsp;&nbsp;
				<span class="button">
					<a href="view.jsp?no=<%= no %>&page=<%= pno %>&r=n">취소</a>
				</span>
				<br /><br />
				<span class="error" id="error_password"></span>
			</div>		
		</form>
	</div>
</body>
</html>