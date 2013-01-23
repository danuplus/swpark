package org.ahsan.swpark.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ahsan.swpark.domain.Notice;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class NoticeIDao {

	private static NoticeIDao dao;
	private SqlSessionFactory ssf;
	private static final int ARTICLE_PER_PAGE = 15;
	
	public static NoticeIDao getInstance() throws IOException {
		if(dao == null) {
			dao = new NoticeIDao();
		}
		return dao;
	}
	
	private NoticeIDao() throws IOException {
		String resource = "mybatis/mybatice-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		
		ssf = new SqlSessionFactoryBuilder().build(is);
	}
	
	public List<Notice> getAllArticleByPage(int page) {
		SqlSession session = ssf.openSession();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", (page-1)*ARTICLE_PER_PAGE);
		map.put("stop", ARTICLE_PER_PAGE);
		
		List<Notice> articles = session.selectList("mybatis.MapperNotice.getAllArticlePage", map);
		
		session.close();
		return articles;
	}
	
	public int getMaxPage() {
		SqlSession session = ssf.openSession();
		
		int total = session.selectOne("mybatis.MapperNotice.getMaxPage");
		int maxPage = (int)Math.ceil(total / (double)ARTICLE_PER_PAGE);
		
		session.close();
		return maxPage;
	}
	
	public Notice getArticleByNo(int no) {
		SqlSession session = ssf.openSession();
		
		Notice article = session.selectOne("mybatis.MapperNotice.getArticleByNo", no);
		
		session.close();
		return article;
	}
	
	public boolean addReadCount(int no) {
		SqlSession session = ssf.openSession();
		
		int result = session.update("mybatis.MapperNotice.addReadCount", no);
		session.close();
		
		if(result > 0)
			return true;
		else 
			return false;
	}
	
	public boolean addArticle(Notice article) {
		SqlSession session = ssf.openSession();
		
		int result = session.insert("mybatis.MapperNotice.addArticle", article);
		session.close();
		
		if(result > 0)
			return true;
		else 
			return false;
	}
	
	public boolean editArticle(Notice article) {
		SqlSession session = ssf.openSession();
		
		int result = session.update("mybatis.MapperNotice.editArticle", article);
		session.close();
		
		if(result > 0)
			return true;
		else 
			return false;
	}
	
	public boolean removeArticle(int no) {
		SqlSession session = ssf.openSession();
		
		int result = session.delete("mybatis.MapperNotice.removeArticle", no);
		session.close();
		
		if(result > 0)
			return true;
		else 
			return false;
	}
	
	public boolean checkPassword(int no, String password) {
		SqlSession session = ssf.openSession();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		
		int result = session.selectOne("mybatis.MapperNotice.checkPasword", map);
		session.close();
		
		if(result > 0)
			return true;
		else 
			return false;
	}
}
