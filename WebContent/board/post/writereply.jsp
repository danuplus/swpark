<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Post, org.ahsan.swpark.domain.PostReply" %>
<%@ page import="org.ahsan.swpark.dao.PostDao" %>
<%
	request.setCharacterEncoding("UTF-8");
	// 글번호가 없으면 글 목록으로 돌아가기
	if(request.getParameter("no")==null || request.getParameter("page")==null ||
		request.getParameter("writer")==null || request.getParameter("password")==null ||
		request.getParameter("memo")==null) {
		response.sendRedirect("list.jsp");
		return;
	} 
	
	int no = Integer.parseInt(request.getParameter("no"));		
	int	pno = Integer.parseInt(request.getParameter("page"));
	
	PostDao dao = new PostDao();
	PostReply reply = new PostReply();
	reply.setWriter(request.getParameter("writer"));
	reply.setPassword(request.getParameter("password"));
	reply.setMemo(request.getParameter("memo"));
	reply.setRef_no(no);
	
	dao.addReply(reply);
	dao.addReplyCount(no);
	dao.close();
	
	response.sendRedirect("view.jsp?no=" + no + "&page=" + pno + "&r=n");
%>
