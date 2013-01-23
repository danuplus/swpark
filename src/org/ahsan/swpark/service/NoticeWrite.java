package org.ahsan.swpark.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahsan.swpark.dao.NoticeIDao;
import org.ahsan.swpark.domain.Notice;

/**
 * Servlet implementation class NoticeWrite
 */
@WebServlet("/NoticeWrite")
public class NoticeWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.sendRedirect("NoticeIndex");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		
		Notice article = new Notice();
		article.setWriter(req.getParameter("writer"));
		article.setPassword(req.getParameter("password"));
		article.setTitle(req.getParameter("title"));
		article.setNotice(req.getParameter("notice"));
		
		NoticeIDao dao = NoticeIDao.getInstance();
		
		if(dao.addArticle(article)) {
			res.sendRedirect("NoticeIndex");
		} else {
			throw new ServletException("데이터베이스 쓰기 작업 실패");
		}
	}
}
