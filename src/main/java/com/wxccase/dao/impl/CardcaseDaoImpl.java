package com.wxccase.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wxccase.dao.CardcaseDao;
import com.wxccase.entity.Cardcase;

public class CardcaseDaoImpl implements CardcaseDao{

	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insertCard(Cardcase cardinfo) {
		SqlSession os = sqlSessionFactory.openSession();
		os.insert("insertCard",cardinfo);
		os.close();
	}

	@Override
	public void updateCard(Cardcase cardinfo) {
		SqlSession os = sqlSessionFactory.openSession();
		os.update("updateCard", cardinfo);
		os.close();
	}

	@Override
	public List<Cardcase> selectCard(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		List<Cardcase> list = os.selectList("selectCard", map);
		os.close();
		return list;
	}

	@Override
	public String selectCardCount(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		String count = os.selectOne("selectCardCount", map);
		os.close();
		return count;
	}

	@Override
	public void deleteCard(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		os.delete("deleteCard", map);
		os.close();
	}

}
