<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao, java.util.*" %>
<%
	if(request.getParameter("no")==null || request.getParameter("page")==null) {
		response.sendRedirect("list.jsp");
		return;
	} 

	BoardDao dao = new BoardDao();
	
	int no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("page"));
	
	Board board = dao.getBoard(no);
	
	if(request.getParameter("r")==null) {
		dao.addReadCount(no);
	} 
	
	List<BoardReply> replies = dao.getReplyByNo(no);
	
	dao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내용 보기</title>
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

		if(checkNull(document.fm.memo, document.getElementById("error_memo"), "내용을 입력해주세요")) result = false;
		
		if(result) {
			document.fm.submit();
		}
	}
</script>
</head>
<body>
<div id="content">
	<h3>내용 보기</h3>
	<table>
		<tr>
			<th width="60">작성자</th>
			<td><%= board.getWriter() %></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%= board.getWdate() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%= board.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td height="100" valign="top"><%= board.getContent().replace("\r\n", "<br />") %></td>
		</tr>
	</table><br />
	<div>
		<span class="button"><a href="list.jsp?page=<%= pno %>">목 록</a></span>&nbsp;&nbsp;
		<span class="button"><a href="answer.jsp?no=<%= no %>&page=<%= pno %>">답 글</a></span>&nbsp;&nbsp;
		<span class="button"><a href="checkboardpass.jsp?no=<%= no %>&page=<%= pno %>&job=update">수 정</a></span>&nbsp;&nbsp;
		<span class="button"><a href="checkboardpass.jsp?no=<%= no %>&page=<%= pno %>&job=delete">삭 제</a></span>
	</div><br />
	<% if(board.getReply_cnt() > 0) { %>
	<h3>덧글</h3>
	<table>
		<tr>
			<th width="60">작성자</th>
			<th>내용</th>
			<th width="80">작성일</th>
		</tr>
		<% for(BoardReply reply : replies) { %>
		<tr>
			<td align="center"><%= reply.getWriter() %></td>
			<td>
				<%= reply.getMemo().replace("\r\n", "<br />") %>&nbsp;
				<span class="small">
					<a href="checkreplypass.jsp?no=<%= no %>&page=<%= pno %>&rno=<%= reply.getNo() %>">삭제</a>
				</span>
			</td>
			<td align="center"><%= reply.getWdate() %></td>
		</tr>
		<% } %>
	</table>
	<% } %><br />
	
	<h3>덧글 달기</h3>
		<form name="fm" action="addreply.jsp" method="post">
			<input type="hidden" name="no" value="<%= no %>" />
			<input type="hidden" name="page" value="<%= pno %>" />
			<table>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" />
						<span class="error" id="error_writer"></span></td>
				</tr>
				<tr>
					<th>암호</th>
					<td><input type="password" name="password" />
						<span class="error" id="error_password"></span></td>
				</tr>
				<tr>
					<th>암호 확인</th>
					<td><input type="password" name="password2" />
						<span class="error" id="error_password2"></span></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="3" cols="80" name="memo"></textarea><br /><br />
						<span class="error" id="error_memo"></span></td>
				</tr>
			</table>
			<br />
			<div align="center">
				<span class="button"><a href="javascript:submit()">확 인</a></span>
			</div>
		</form>
</div>
</body>
</html>