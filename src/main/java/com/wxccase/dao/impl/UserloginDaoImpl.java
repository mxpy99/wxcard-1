package com.wxccase.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.wxccase.dao.UserloginDao;
import com.wxccase.entity.Userlogin;

@Repository
public class UserloginDaoImpl implements UserloginDao{

	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void insertUserlogin(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		os.insert("insertUserlogin", map);
		os.close();
	}

	@Override
	public void updateUserlogin(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		os.update("updateUserlogin", map);
		os.close();
	}

	@Override
	public Userlogin selectUserlogin(Map map) {
		SqlSession os = sqlSessionFactory.openSession();
		Userlogin userlogin  = os.selectOne("selectUserlogin", map);
		os.close();
		return userlogin;
	}

}
