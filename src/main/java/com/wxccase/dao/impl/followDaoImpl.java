package com.wxccase.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wxccase.dao.FollowDao;

public class followDaoImpl implements FollowDao{
	
	@Resource 
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insertFollow(Map map) throws Exception {
		SqlSession os = sqlSessionFactory.openSession();
		os.insert("insertFollow", map);
		os.close();
	}
	
	@Override
	public void deleteFollow(Map map) throws Exception {
		SqlSession os = sqlSessionFactory.openSession();
		os.insert("insertFollow", map);
		os.close();
	}

	@Override
	public List<Map> listFollow(Map map) throws Exception {
		SqlSession os = sqlSessionFactory.openSession();
		List<Map> list = os.selectList("listFollow", map);
		os.close();
		return list;
	}

	@Override
	public List<Map> listUser(Map map) throws Exception {
		SqlSession os = sqlSessionFactory.openSession();
		List<Map> list = os.selectList("listUser", map);
		os.close();
		return list;
	}
}
