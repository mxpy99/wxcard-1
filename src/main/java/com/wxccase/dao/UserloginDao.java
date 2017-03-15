package com.wxccase.dao;

import java.util.Map;

import com.wxccase.entity.Userlogin;

public interface UserloginDao {
	/**
	 * 新增 
	 * @param map
	 */
	public void insertUserlogin(Map map);
	/**
	 * 更新
	 * @param map
	 */
	public void updateUserlogin(Map map);
	/**
	 * 查找  无分页
	 * @param map
	 */
	public Userlogin selectUserlogin(Map map);
}
