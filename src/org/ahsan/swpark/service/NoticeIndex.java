package org.ahsan.swpark.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahsan.swpark.dao.NoticeIDao;
import org.ahsan.swpark.domain.Notice;

/**
 * Servlet implementation class NoticeIndex
 */
@WebServlet("/NoticeIndex")
public class NoticeIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeIndex() {
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
		
		NoticeIDao dao = NoticeIDao.getInstance();
		List<Notice> articles = dao.getAllArticleByPage(page);
		int total = dao.getMaxPage();
		
		req.setAttribute("page", page);
		req.setAttribute("total", total);
		req.setAttribute("articles", articles);
		
		RequestDispatcher dp = req.getRequestDispatcher("board/noticei/index.jsp");
		dp.forward(req, res);
	}
}
