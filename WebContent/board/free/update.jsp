<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao" %>
<%
	if(request.getParameter("no")==null || request.getParameter("page")==null ||
		session.getAttribute("no")==null) {
		response.sendRedirect("list.jsp");
		return;
	}

	int no = ((Integer)session.getAttribute("no")).intValue();
	int pno = Integer.parseInt(request.getParameter("page"));
	
	BoardDao dao = new BoardDao();
	Board board = dao.getBoard(no);
	dao.close();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
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
	<h3>글 수정</h3>
	<form action="updatepost.jsp" method="post" name="fm">
		<input type="hidden" name="no" value="<%= no %>" />
		<input type="hidden" name="page" value="<%= pno %>" />
		<table>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="writer" value="<%= board.getWriter() %>"/>
					<span class="error" id="error_writer"></span>
				</td>
			</tr>
			<tr>
				<th>암호</th>
				<td>
					<input type="password" name="password" value="<%= board.getPassword() %>" />
					<span class="error" id="error_password"></span>
				</td>
			</tr>
			<tr>
				<th>암호확인</th>
				<td>
					<input type="password" name="password2" value="<%= board.getPassword() %>" />
					<span class="error" id="error_password2"></span>
				</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td>
					<input type="text" name="title" size="80" value="<%= board.getTitle() %>" />
					<span class="error" id="error_title"></span>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea rows="25" cols="80" name="content"><%= board.getContent() %></textarea></td>
			</tr>
		</table><br />
		<div align="center">
			<span class="button"><a href="javascript:submit()">확 인</a></span>&nbsp;&nbsp;
			<span class="button"><a href="view.jsp?no=<%= no %>&page=<%= pno %>&r=n">취 소</a></span>
		</div>
	</form>
</div>
</body>
</html>