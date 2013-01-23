<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Notice, org.ahsan.swpark.dao.NoticeDao" %>
<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("no")==null) {
		response.sendRedirect("list.jsp");
		return;
	}
	
	int no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("pno"));
	
	Notice notice = new Notice();
	notice.setNo(no);
	notice.setTitle(request.getParameter("title"));
	notice.setNotice(request.getParameter("notice"));
	notice.setWriter(request.getParameter("writer"));
	notice.setPassword(request.getParameter("password"));
	
	NoticeDao dao = new NoticeDao();
	dao.editNotice(notice);
	dao.close();
	
	response.sendRedirect("list.jsp?pno=" + pno);
%>
