package org.ahsan.swpark.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ahsan.swpark.dao.NoticeIDao;
import org.ahsan.swpark.domain.Notice;

/**
 * Servlet implementation class NoticeView
 */
@WebServlet("/NoticeView")
public class NoticeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(req.getParameter("no"));
		
		NoticeIDao dao = NoticeIDao.getInstance();
		Notice article = dao.getArticleByNo(no);
		
		if(article.getNotice()!=null) {
			article.setNotice(article.getNotice().replace("\r\n", "<br />"));
		}
		
		if(req.getParameter("r")==null) {
			dao.addReadCount(no);
		}
		
		req.setAttribute("article", article);
		RequestDispatcher dp = req.getRequestDispatcher("board/noticei/view.jsp");
		dp.forward(req, res);
	}
}
