<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Post, org.ahsan.swpark.domain.PostReply" %>
<%@ page import="org.ahsan.swpark.dao.PostDao" %>
<%
	int no=0, pno=0;
	Post post = null;
	
	if(request.getParameter("no")==null || request.getParameter("page")==null ||
		session.getAttribute("no")==null) {
		
		response.sendRedirect("list.jsp");
		return;
	} else {
		no = Integer.parseInt(request.getParameter("no"));
		pno = Integer.parseInt(request.getParameter("page"));
		
		PostDao dao = new PostDao();
		post = dao.getPost(no);
		dao.close();
	
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글수정</title>
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
		<h3>글 수정</h3>
		<form name="fm" action="editpost.jsp?no=<%= no %>&page=<%= pno %>" method="post">
			<table>
				<tr>
					<th width="60">작성자</th>
					<td>
						<input type="text" name="writer" value="<%= post.getWriter() %>"/>
						<span class="error" id="error_writer"></span>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="password" value="<%= post.getPassword() %>"/>
						<span class="error" id="error_password"></span>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" size="80" name="title" value="<%= post.getTitle() %>" />
						<span class="error" id="error_title"></span>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="content" rows="25" cols="80"><%= post.getContent() %></textarea>
					</td>
				</tr>
			</table>
			<br />
			<div align="center">
				<span class="button">
					<a href="javaScript:submit()">확 인</a>
				</span>&nbsp;&nbsp;&nbsp;
				<span class="button">
					<a href="list.jsp">취 소</a>
				</span>
			</div>
		</form>
	</div>
</body>
</html>