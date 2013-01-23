<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao" %>
<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("no")==null || request.getParameter("page")==null ||
		session.getAttribute("no")==null) {
		
		response.sendRedirect("list.jsp");
		
	} else {
		
		int no = ((Integer)session.getAttribute("no")).intValue();
		session.invalidate();
		int pno = Integer.parseInt(request.getParameter("page"));
		
		BoardDao dao = new BoardDao();

		dao.removeBoard(no); 
		dao.close();
		
		response.sendRedirect("list.jsp?page=" + pno + "&r=n");
	}

%>