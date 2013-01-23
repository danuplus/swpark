<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="org.ahsan.swpark.dao.NoticeDao, org.ahsan.swpark.domain.Notice" %>
<%
	if(request.getAttribute("no") != null && request.getAttribute("pno") != null) {
		int no = ((Integer)request.getAttribute("no")).intValue();
		int pno = ((Integer)request.getAttribute("pno")).intValue();
		
		NoticeDao dao = new NoticeDao();
		dao.removeNotice(no);
		dao.close();
		response.sendRedirect("list.jsp?pno=" + pno);
	} else {
		response.sendRedirect("list.jsp");
	}
%>
