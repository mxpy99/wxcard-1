package com.wxccase.service;

import java.util.List;
import java.util.Map;

import com.wxccase.entity.CardClassify;
import com.wxccase.entity.Cardcase;

public interface ClassifyService {
	/**
	 * 分类的修改
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map updateClassify(Map map) throws Exception;
	/**
	 * 分类的新增
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map insertClassify(Map map) throws Exception;
	/**
	 * 分类的删除
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map deleteClassify(Map map) throws Exception;
	/**
	 * 分类的查询
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CardClassify> selectClassify(Map map) throws Exception;
	/**
	 * 分类详情查询
	 * @param map
	 * @return
	 */
	public int selectClassifyDetail(Map map) throws Exception;
}
