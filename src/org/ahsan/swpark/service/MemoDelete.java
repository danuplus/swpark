package org.ahsan.swpark.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahsan.swpark.dao.MemoDao;
/**
 * Servlet implementation class MemoDelete
 */
@WebServlet("/MemoDelete")
public class MemoDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("MemoIndex");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("no")==null || request.getParameter("page")==null) {
			response.sendRedirect("MemoIndex");
			
		} else {
			int no = Integer.parseInt(request.getParameter("no"));
			int page = Integer.parseInt(request.getParameter("page"));
			String password = request.getParameter("password");
			
			try {
				MemoDao dao = new MemoDao();
				boolean success = dao.checkPassword(no, password);
				
				if(success) {
					dao.removeMemo(no);
					response.sendRedirect("MemoIndex");
					
				} else {
					response.sendRedirect("memo2/fail.jsp?page=" + page);
				}
				
				dao.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ServletException(e);
			} 
		}
	}
}
