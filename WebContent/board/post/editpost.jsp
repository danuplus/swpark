<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Post, org.ahsan.swpark.domain.PostReply" %>
<%@ page import="org.ahsan.swpark.dao.PostDao" %>
<%
	request.setCharacterEncoding("UTF-8");

	if(session.getAttribute("no")==null || request.getParameter("no")==null || 
	   request.getParameter("page")==null) {
		response.sendRedirect("list.jsp");
	} else {
		int no = ((Integer)session.getAttribute("no")).intValue();
		session.invalidate();
		int pno = Integer.parseInt(request.getParameter("page"));
		
		Post post = new Post();
		post.setNo(no);
		post.setWriter(request.getParameter("writer"));
		post.setPassword(request.getParameter("password"));
		post.setTitle(request.getParameter("title"));
		post.setContent(request.getParameter("content"));
		
		PostDao dao = new PostDao();
		dao.editPost(post);
		dao.close();
		
		response.sendRedirect("view.jsp?no=" + no + "&page=" + pno + "&r=n");
	}
%>