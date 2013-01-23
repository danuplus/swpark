<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Post, org.ahsan.swpark.domain.PostReply" %>
<%@ page import="org.ahsan.swpark.dao.PostDao" %>
<%
	request.setCharacterEncoding("UTF-8");
	if(request.getParameter("writer")==null || 
		request.getParameter("password")==null ||
	   	request.getParameter("title")==null) {
		
		response.sendRedirect("list.jsp");
		return;
	} else {
		Post post = new Post();
		post.setWriter(request.getParameter("writer"));
		post.setPassword(request.getParameter("password"));
		post.setTitle(request.getParameter("title"));
		post.setContent(request.getParameter("content"));
		
		PostDao dao = new PostDao();
		dao.addPost(post);
		dao.close();
		
		response.sendRedirect("list.jsp");
	}

%>    