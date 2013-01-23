package org.ahsan.swpark.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ahsan.swpark.domain.Memo;
import org.ahsan.swpark.dao.MemoDao;
/**
 * Servlet implementation class MemoIndex
 */
@WebServlet("/MemoIndex")
public class MemoIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			MemoDao dao = new MemoDao();
			
			int page = 1;
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			List<Memo> memos = dao.getMemoByPage(page);
			request.setAttribute("memos", memos);
			
			request.setAttribute("currentPage", page);
			
			int totalPage = dao.getTotalPage();
			request.setAttribute("totalPage", totalPage);
			
			dao.close();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("memo2/index.jsp");
			dispatcher.forward(request, response);
			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
}
