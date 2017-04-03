package com.wxccase.dao;

import java.util.Map;

import com.wxccase.entity.Userinfo;

public interface UserinfoDao {
	/**
	 * 新增 
	 * @param map
	 */
	public void insertUserinfo(Map map) throws Exception;
	/**
	 * 更新
	 * @param map
	 */
	public void updateUserinfo(Map map) throws Exception;
	/**
	 * 查找 无分页
	 * @param map
	 */
	public Userinfo selectUserinfo(Map map) throws Exception;
	/**
	 * 更新 logo
	 * @param map
	 * @author viakiba
	 * @time 2017-04-03
	 * 892645423@qq.com
	 */
	public void updateLogo(Map map) throws Exception;
	/**
	 * 进行反馈
	 * @param map
	 * @author viakiba
	 * @time 2017-04-03
	 * 892645423@qq.com
	 */
	public void insertFaq(Map map) throws Exception;
	
}
