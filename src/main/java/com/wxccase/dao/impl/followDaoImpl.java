package com.wxccase.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.wxccase.dao.FollowDao;

@Repository
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
		os.insert("deleteFollow", map);
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

	@Override
	public Map selectByuseridAndcardid(Map map) throws Exception {
		SqlSession os = sqlSessionFactory.openSession();
		map = (Map) os.selectOne("selectByuseridAndcardid", map);
		os.close();
		return map;
	}
}
