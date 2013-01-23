<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
<link rel="stylesheet" type="text/css" href="css/memoi.css" >
<script type="text/javascript" src="script/util.js"></script>
<script type="text/javascript">
function checkForm() {
	var result = true;
	
	if(checkNull(document.fm.writer, document.getElementById("error_writer"), "작성자를 입력하세요")) result = false;
	if(checkNull(document.fm.password, document.getElementById("error_password"), "암호를 입력하세요")) result = false;
	if(!isEqual(document.fm.password, document.fm.password2, 
			document.getElementById("error_password2"), "암호가 일치하지 않습니다")) result = false;
	if(checkNull(document.fm.memo, document.getElementById("error_memo"), "메모를 입력하세요")) result = false;
	
	return result;
}
</script>
</head>
<body>
<div id="content">
	<h3>글 목록</h3>
	<div align="right">전체 ${total} 페이지 중에 현재 ${page } 페이지</div>
	<table>
		<tr>
			<th width="60">글번호</th>
			<th>메모</th>
			<th width="60">작성자</th>
			<th width="80">작성일</th>
		</tr>
		<c:forEach var="memo" items="${memos }">
		<tr>
			<td align="center">${memo.no }</td>
			<td>${memo.memo }&nbsp;
				<a href="board/memoi/password.jsp?no=${memo.no }&page=${page}" class="small">삭제</a>
			</td>
			<td align="center">${memo.writer }</td>
			<td align="center">
				<fmt:formatDate value="${memo.date }" type="date" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
		</c:forEach>
		<tr align="center">
			<td colspan="4">
				<c:if test="${page > 2 }">
					<a href="MyIndex?page=1">&lt;&lt;첫 페이지</a>
				</c:if>&nbsp;&nbsp;
				<c:if test="${page > 1 }">
					<a href="MyIndex?page=${page-1 }">&lt; 이전 페이지</a>
				</c:if>&nbsp;&nbsp;
				<c:if test="${page < total }">
					<a href="MyIndex?page=${page+1 }">다음페이지 &gt;</a>
				</c:if>&nbsp;&nbsp;
				<c:if test="${page < total-1 }">
					<a href="MyIndex?page=${total }">마지막 페이지&gt;&gt;</a>
				</c:if>
			</td>
		</tr>
	</table><br />
	<h3>메모 쓰기</h3>
	<form name="fm" action="MyWrite" method="post">
			<table>
				<tr>
					<th width="60">작성자</th>
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
					<th>암호 확인</th>
					<td>
						<input type="password" name="password2" />
						<span class="error-text" id="error_password2"></span>
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
				<input type="submit" onclick="return checkForm()" value="등록" />
			</div>
		</form>
</div>
</body>
</html>