package org.ahsan.swpark.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ahsan.swpark.domain.Memoi;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemoiDao {

	public static final int BOARD_PER_PAGE = 15;

	public static MemoiDao dao;
	private SqlSessionFactory sf;

	public static MemoiDao getInstance() throws IOException {
		if (dao == null) {
			dao = new MemoiDao();
		}
		return dao;
	}

	private MemoiDao() throws IOException {
		String rs = "mybatis/mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(rs);

		sf = new SqlSessionFactoryBuilder().build(is);
	}

	public List<Memoi> getAllMemoByPage(int page) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", (page - 1) * BOARD_PER_PAGE);
		map.put("stop", BOARD_PER_PAGE);

		SqlSession session = sf.openSession();
		List<Memoi> memos = session.selectList(
				"mybatis.MapperMemoi.getAllMemoByPage", map);
		session.close();

		return memos;
	}

	public int getTotalPage() {
		SqlSession session = sf.openSession();
		int cnt = session.selectOne("mybatis.MapperMemoi.getTotalPage");
		int total = (int) Math.ceil(cnt / (double) BOARD_PER_PAGE);
		session.close();

		return total;
	}

	public boolean addMemo(Memoi memo) {
		SqlSession session = sf.openSession();
		int result = session.insert("mybatis.MapperMemoi.addMemo", memo);
		session.close();

		if (result > 0)
			return true;
		else
			return false;
	}

	public boolean removeMemo(int no) {
		SqlSession session = sf.openSession();
		int result = session.delete("mybatis.MapperMemoi.removeMemo", no);
		session.close();

		if (result > 0)
			return true;
		else
			return false;
	}

	public boolean checkPassword(int no, String password) {
		SqlSession session = sf.openSession();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);

		int result = session
				.selectOne("mybatis.MapperMemoi.checkPassword", map);
		session.close();

		if (result > 0)
			return true;
		else
			return false;
	}
}
