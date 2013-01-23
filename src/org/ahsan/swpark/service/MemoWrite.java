package org.ahsan.swpark.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ahsan.swpark.domain.Memo;
import org.ahsan.swpark.dao.MemoDao;
/**
 * Servlet implementation class MemoWrite
 */
@WebServlet("/MemoWrite")
public class MemoWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoWrite() {
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
		String writer = request.getParameter("writer");
		String password = request.getParameter("password");
		String memo = request.getParameter("memo");
		
		Memo commt = new Memo();
		commt.setWriter(writer);
		commt.setPassword(password);
		commt.setMemo(memo);
		
		try {
			MemoDao dao = new MemoDao();
			dao.addMemo(commt);
			dao.close();
			
			response.sendRedirect("MemoIndex");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

}
