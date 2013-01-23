package org.ahsan.swpark.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahsan.swpark.dao.MemoiDao;
import org.ahsan.swpark.domain.Memoi;

/**
 * Servlet implementation class MyIndex
 */
@WebServlet("/MyIndex")
public class MyIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page = 1;
		if(req.getParameter("page")!=null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		MemoiDao dao = MemoiDao.getInstance();
		
		int total = dao.getTotalPage();
		
		List<Memoi> memos = dao.getAllMemoByPage(page);
		//req.setAttribute("memos", memos);
		
		req.setAttribute("page", page);
		req.setAttribute("total", total);
		req.setAttribute("memos", memos);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("board/memoi/index.jsp");
		dispatcher.forward(req, res);
	}
}