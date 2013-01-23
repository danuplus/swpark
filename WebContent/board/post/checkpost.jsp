<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../../error/error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Post, org.ahsan.swpark.domain.PostReply" %>
<%@ page import="org.ahsan.swpark.dao.PostDao" %>
<%
	if(request.getParameter("no")==null || request.getParameter("page")==null ||
		request.getParameter("job")==null || request.getParameter("password")==null) {
		response.sendRedirect("list.jsp");
		return;
	} 

	int no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("page"));
	String password = request.getParameter("password");
	String job = request.getParameter("job");

	int rno = 0;
	if(job.equals("remove_reply")) {
		if(request.getParameter("rno")==null) {
			response.sendRedirect("list.jsp");
			return;
		} else {
			rno = Integer.parseInt(request.getParameter("rno"));	
		}
	}
	
	PostDao dao = new PostDao();
	
	boolean result = true;
	String url = "";
	if(job.equals("edit_post") || job.equals("remove_post")) {
		
		if(dao.checkPassword(no, password, PostDao.POST)) {
			session.setAttribute("no", no);
			
			if(job.equals("edit_post")) {
				url = "edit.jsp?no=" + no + "&page=" + pno;
			} else {
				url = "removepost.jsp?no=" + no + "&page=" + pno;
			}
			
		} else {
			url = "check.jsp?no=" + no + "&page=" + pno + "&job=" + job + "&result=fail";
		}
	}
	
	if(job.equals("remove_reply")) {
		if(dao.checkPassword(rno, password, PostDao.REPLY)) {
			session.setAttribute("no", rno);
			url = "removereply.jsp?no=" + no + "&page=" + pno;
			
		} else {
			url = "check.jsp?no=" + no + "&page=" + pno + "&job=" + job + "&rno=" + rno + "&result=fail";
		}
	}
	
	dao.close();
	
	response.sendRedirect(url);
%>