package com.wxccase.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.wxccase.dao.UserinfoDao;
import com.wxccase.entity.Userinfo;

@Repository
public class UserinfoDaoImpl implements UserinfoDao{

	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insertUserinfo(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		os.insert("insertUserinfo", map);
		os.close();
	}

	@Override
	public void updateUserinfo(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		os.update("updateUserinfo", map);
		os.close();
	}

	@Override
	public Userinfo selectUserinfo(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		Userinfo userinfo = os.selectOne("selectUserinfo", map);
		os.close();
		return userinfo;
	}
	
}
