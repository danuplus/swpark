<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao" %>
<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("no")==null || request.getParameter("page")==null 
		|| request.getParameter("job")==null || request.getParameter("password")==null) {
		response.sendRedirect("list.jsp");
		return;
	}
	
	int no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("page"));
	String job = request.getParameter("job");
	String password = request.getParameter("password");
	
	BoardDao dao = new BoardDao();
	boolean result = dao.checkPassword(no, password);
	dao.close();
	
	if(result) {
		session.setAttribute("no", no);
		response.sendRedirect(job + ".jsp?no=" + no + "&page=" + pno);
		
	} else {
		response.sendRedirect("fail.jsp?no=" + no + "&page=" + pno + "&r=n");
	}
%>