<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Notice, org.ahsan.swpark.dao.NoticeDao" %>
<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("title") != null) {
		Notice notice = new Notice();
		notice.setTitle(request.getParameter("title"));
		notice.setNotice(request.getParameter("notice"));
		notice.setWriter(request.getParameter("writer"));
		notice.setPassword(request.getParameter("password"));
		
		NoticeDao noticeDao = new NoticeDao();
		noticeDao.addNotice(notice);
		noticeDao.close();
	}
	response.sendRedirect("list.jsp");
%>
