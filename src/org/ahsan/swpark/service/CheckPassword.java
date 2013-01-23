package org.ahsan.swpark.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahsan.swpark.dao.MemoiDao;
/**
 * Servlet implementation class CheckPassword
 */
@WebServlet("/CheckPassword")
public class CheckPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.sendRedirect("MyIndex");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		String password = request.getParameter("password");
		
		MemoiDao dao = MemoiDao.getInstance();
		if(dao.checkPassword(no, password)) {
			if(dao.removeMemo(no)) {
				response.sendRedirect("MyIndex?page=" + page);
			}else {
				throw new ServletException("데이터버이스에 삭제 작업을 수행 할 수 없습니다.");
			}
		}else{
			RequestDispatcher fail = request.getRequestDispatcher("board/memoi/fail.jsp");
			fail.forward(request, response);
		}
	}

}
