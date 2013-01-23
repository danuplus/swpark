package org.ahsan.swpark.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahsan.swpark.dao.MemoiDao;
import org.ahsan.swpark.domain.Memoi;

/**
 * Servlet implementation class MyWrite
 */
@WebServlet("/MyWrite")
public class MyWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyWrite() {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		MemoiDao dao = MemoiDao.getInstance();
		
		Memoi memo = new Memoi();
		memo.setWriter(req.getParameter("writer"));
		memo.setPassword(req.getParameter("password"));
		memo.setMemo(req.getParameter("memo"));
		
		if(dao.addMemo(memo)) {
			res.sendRedirect("MyIndex");
		}else{
			throw new ServletException("데이터베이스에 쓰기 작업이 실패했습니다.");
		}
	}

}
