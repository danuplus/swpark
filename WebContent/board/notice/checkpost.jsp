<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="org.ahsan.swpark.domain.Notice, org.ahsan.swpark.dao.NoticeDao" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	// 글번호가 없으면 목록으로 복귀
	if(request.getParameter("no") == null) {
		response.sendRedirect("list.jsp");
		return;
	}
	
	// 파라메터 읽기
	int no = Integer.parseInt(request.getParameter("no"));
	int pno = Integer.parseInt(request.getParameter("pno"));
	String job = request.getParameter("job");
	String password = request.getParameter("password");
	
	NoticeDao dao = new NoticeDao();
	boolean result = dao.checkPassword(no, password);
	dao.close();
	
	if(result) {
		String url = "";
		
		if(job.equals("edit")) {
			url = "edit.jsp";
		} else if(job.equals("remove")) {
			url = "remove.jsp";
		}
		
		request.setAttribute("no", no);
		request.setAttribute("pno", pno);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}else {
		response.sendRedirect("check.jsp?no=" + no + "&pno=" + pno + "&job" + job + "&result=fail");
	}
%>
