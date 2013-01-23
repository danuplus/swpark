<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>한줄 메모</title>
<link rel="stylesheet" href="css/memo2.css" >
<script type="text/javascript">
function submit() {
	var result = true;
	
	if(checkNull(document.fm.writer, document.getElementById("error_writer"), "작성자를 입력해주세요")) result = false;
	if(checkNull(document.fm.password, document.getElementById("error_password"), "암호를 입력해주세요")) result = false;
	if(checkNull(document.fm.memo, document.getElementById("error_memo"), "메모를 입력해주세요")) result = false;
	
	if(result)
		document.fm.submit();	
}

function checkNull(src, target, message) {
	if(src.value==null || src.value=="") {
		src.setAttribute("class", "error-border");
		target.innerHTML = message;
		return true;
	} else {
		src.setAttribute("class", "");
		target.innerHTML = "";
		return false;
	}
}
</script>
</head>
<body>
	<div id="content">
		<h3>글 목록</h3>
		<div align="right">
			전체 ${totalPage } 페이지 중에 ${currentPage } 페이지
		</div>
		<table>
			<tr>
				<th width="50">글번호</th>
				<th>메모</th>
				<th width="50">작성자</th>
				<th width="80">작성일</th>
			</tr>
			<c:forEach var="memo" items="${memos}">
			<tr>
				<td align="center">${memo.no }</td>
				<td>${memo.memo }&nbsp;<a class="small" href="memo2/checkPassword.jsp?no=${memo.no }&page=${currentPage }">삭제</a></td>
				<td align="center">${memo.writer }</td>
				<td align="center">${memo.date }</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="center">
					<c:if test="${currentPage > 1 }">
						<a href="MemoIndex?page=1">&lt;&lt;첫 페이지</a>
					</c:if>&nbsp;&nbsp;
					<c:if test="${currentPage > 1 }">
						<a href="MemoIndex?page=${currentPage-1 }">&lt;이전 페이지</a>
					</c:if>&nbsp;&nbsp;
					<c:if test="${currentPage < totalPage }">
						<a href="MemoIndex?page=${currentPage+1 }">다음 페이지&gt;</a>
					</c:if>&nbsp;&nbsp;
					<c:if test="${currentPage < totalPage }">
						<a href="MemoIndex?page=${totalPage }">마지막 페이지&gt;&gt;</a>
					</c:if>
				</td>
			</tr>
		</table><br />
		
		<h3>메모 남기기</h3>
		<form name="fm" action="MemoWrite" method="post">
			<table>
				<tr>
					<th width="50">작성자</th>
					<td>
						<input type="text" name="writer" />
						<span class="error-text" id="error_writer"></span>
					</td>
				</tr>
				<tr>
					<th>암호</th>
					<td>
						<input type="password" name="password" />
						<span class="error-text" id="error_password"></span>
					</td>
				</tr>
				<tr>
					<th>메모</th>
					<td>
						<textarea rows="3" cols="85" name="memo"></textarea><br /><br />
						<span class="error-text" id="error_memo"></span>
					</td>
				</tr>
			</table>
			<br />
			<div align="center">
				<span class="button"><a href="javaScript:submit()">등 록</a></span>
			</div>
		</form>
	</div>
</body>
</html>