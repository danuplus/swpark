<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Board, org.ahsan.swpark.domain.BoardReply" %>
<%@ page import="org.ahsan.swpark.dao.BoardDao" %>
<%
	request.setCharacterEncoding("UTF-8");

	if(request.getParameter("no")==null || request.getParameter("page")==null ||
		session.getAttribute("no")==null ||	request.getParameter("writer")==null || 
		request.getParameter("password")==null || request.getParameter("title")==null || 
		request.getParameter("content")==null) {
		
		response.sendRedirect("list.jsp");
		
	} else {
		
		int no = ((Integer)session.getAttribute("no")).intValue();
		session.invalidate();
		int pno = Integer.parseInt(request.getParameter("page"));
		
		BoardDao dao = new BoardDao();
		Board board = new Board();
		board.setNo(no);
		board.setWriter(request.getParameter("writer"));
		board.setPassword(request.getParameter("password"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
	
		dao.editBoard(board); 
		dao.close();
		
		response.sendRedirect("view.jsp?no=" + no + "&page=" + pno + "&r=n");
	}

%>