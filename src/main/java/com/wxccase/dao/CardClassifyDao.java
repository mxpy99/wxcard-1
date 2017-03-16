package com.wxccase.dao;

import java.util.List;
import java.util.Map;

import com.wxccase.entity.CardClassify;

public interface CardClassifyDao {
	/**
	 * 新增 
	 * @param map
	 */
	public void insertClassify(Map map);
	/**
	 * 更新
	 * @param map
	 */
	public void updateClassify(Map map);
	/**
	 * 查找  分类列表  无分页
	 * @param map
	 */
	public List<CardClassify> selectClassify(Map map);
	/**
	 * 删除
	 * @param map
	 */
	public void deleteClassify(Map map);
	/**
	 * 查找  分类列表  详情
	 * @param map
	 */
	public List<CardClassify> selectClassifyDetail(Map map);
}
