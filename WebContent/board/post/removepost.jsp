<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Post, org.ahsan.swpark.domain.PostReply" %>
<%@ page import="org.ahsan.swpark.dao.PostDao" %>
<%
	if(session.getAttribute("no")==null || request.getParameter("no")==null ||
	   request.getParameter("page")==null) {
		response.sendRedirect("list.jsp");
		
	} else {
		int no = ((Integer)session.getAttribute("no")).intValue();
		session.invalidate();
		int pno = Integer.parseInt(request.getParameter("page"));
		
		PostDao dao = new PostDao();
		dao.removePost(no);
		dao.close();
		
		response.sendRedirect("list.jsp?page=" + pno);
	}
%>