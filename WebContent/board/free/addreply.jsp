<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao" %>
<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("no")==null || request.getParameter("page")==null ||
		request.getParameter("writer")==null || request.getParameter("password")==null ||
		request.getParameter("memo")==null) {
		response.sendRedirect("list.jsp");
		return;
	}

	int no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("page"));

	BoardDao dao = new BoardDao();
	
	BoardReply reply = new BoardReply();
	reply.setWriter(request.getParameter("writer"));
	reply.setPassword(request.getParameter("password"));
	reply.setMemo(request.getParameter("memo"));
	reply.setRef_no(no);
	
	dao.addReply(reply);
	dao.close();
	
	response.sendRedirect("view.jsp?no=" + no + "&page=" + pno + "&r=n");
%>