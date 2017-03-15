package com.wxccase.dao;

import java.util.Map;

import com.wxccase.entity.Userinfo;

public interface UserinfoDao {
	/**
	 * 新增 
	 * @param map
	 */
	public void insertUserinfo(Map map);
	/**
	 * 更新
	 * @param map
	 */
	public void updateUserinfo(Map map);
	/**
	 * 查找 无分页
	 * @param map
	 */
	public Userinfo selectUserinfo(Map map);
}
