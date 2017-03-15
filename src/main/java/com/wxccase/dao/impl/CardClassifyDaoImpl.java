package com.wxccase.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wxccase.dao.CardClassifyDao;
import com.wxccase.entity.CardClassify;

public class CardClassifyDaoImpl implements CardClassifyDao{

	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insertClassify(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		os.insert("insertClassify",map);
		os.close();
		
	}

	@Override
	public void updateClassify(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		os.update("updateClassify", map);
		os.close();
	}

	@Override
	public List<CardClassify> selectClassify(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		List<CardClassify> list = os.selectList("selectClassify", map);
		os.close();
		return list;
	}

	@Override
	public void deleteClassify(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		os.delete("deleteClassify", map);
		os.close();
	}

}
