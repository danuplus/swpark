<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Post, org.ahsan.swpark.domain.PostReply" %>
<%@ page import="org.ahsan.swpark.dao.PostDao, java.util.*" %>
<%
	if(request.getParameter("no")==null || request.getParameter("page")==null) {
		response.sendRedirect("list.jsp");
		return;
	}

	int	no = Integer.parseInt(request.getParameter("no"));		
	int pno = Integer.parseInt(request.getParameter("page"));
	
	PostDao dao = new PostDao();

	if(request.getParameter("r")==null) {
		dao.addRef(no);	
	}
	
	Post post = dao.getPost(no);

	List<PostReply> replys = dao.getAllReply(no);

	dao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글보기</title>
<link rel="stylesheet" href="../../css/post.css" >
<script type="text/javascript">
	function submit() {
		var result = true;
		if(checkNull(document.fm.writer, document.getElementById("error_writer"), "작성자를 입력해주세요")) result = false;
		if(checkNull(document.fm.password, document.getElementById("error_password"), "암호를 입력해주세요")) result = false;
		if(checkNull(document.fm.memo, document.getElementById("error_memo"), "댓글을 입력해주세요")) result = false;
		
		if(result) {
			document.fm.submit();
		}
	}
	
	function checkNull(src, target, message) {
		if(src.value=="" || src.value==null) {
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
	<h3>글보기</h3>
	<table>
		<tr>
			<th width="60">작성자</th>
			<td><%= post.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%= post.getTitle() %></td>
		</tr>
		<tr>
			<th height="300">내용</th>
			<td valign="top"><%= post.getContent().replace("\r\n", "<br />") %></td>
		</tr>
	</table><br />
	<div align="center">
		<span class="button"><a href="list.jsp?page=<%= pno %>">목 록</a></span>
		<span class="button"><a href="check.jsp?no=<%= no %>&page=<%= pno %>&job=edit_post">수 정</a></span>
		<span class="button"><a href="check.jsp?no=<%= no %>&page=<%= pno %>&job=remove_post">삭 제</a></span>
	</div><br />
	
	<h3>덧글</h3>
	<% if(replys.size() > 0) { %>
	<table>
		<thead>
			<tr>
				<th width="60">작성자</th>
				<th>댓글</th>
				<th width="80">작성일</th>
			</tr>
		</thead>
		<tbody>
			<% for(int i=0; i<replys.size(); i++) { %>
			<tr>
				<td align="center"><%= replys.get(i).getWriter() %></td>
				<td>
					<%= replys.get(i).getMemo().replace("\r\n", "<br />") %>&nbsp;
					<span class="small">
						<a href="check.jsp?no=<%= no %>&page=<%= pno %>&job=remove_reply&rno=<%= replys.get(i).getNo() %>">삭제</a>
					</span>
				</td>
				<td align="center"><%= replys.get(i).getWdate() %></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	<% } %>
	<br />
	<form action="writereply.jsp?no=<%= no %>&page=<%= pno %>" name="fm" method="post">
		<table>
			<tr>
				<th width="60">작성자</th>
				<td>
					<input type="text" name="writer" />
					<span class="error" id="error_writer"></span>
				</td>
			</tr>
			<tr>
				<th>암호</th>
				<td>
					<input type="password" name="password" />
					<span class="error" id="error_password"></span>
				</td>
			</tr>
			<tr>
				<th>덧글</th>
				<td>
					<textarea name="memo" id="memo" cols="80" rows="3"></textarea><br />
					<span class="error" id="error_memo"></span>
				</td>
			</tr>
		</table><br />
		<div align="center">
			<span class="button">
				<a href="javascript:submit()">덧글 달기</a>
			</span>&nbsp;&nbsp;
		</div>
	</form>
</div>
</body>
</html>