package com.wxccase.dao;

import java.util.List;
import java.util.Map;

import com.wxccase.entity.Cardcase;

public interface CardcaseDao {
	/**
	 * 新增 
	 * @param map
	 */
	public void insertCard(Cardcase cardinfo) throws Exception;
	/**
	 * 更新
	 * @param map
	 */
	public void updateCard(Cardcase cardinfo) throws Exception;
	/**
	 * 查找  需要分页
	 * @param map
	 */
	public List<Cardcase> selectCard(Map map) throws Exception;
	/**
	 * 查询总记录数
	 * @param map
	 */
	public String selectCardCount(Map map) throws Exception;
	/**
	 * 删除
	 * @param map
	 */
	public void deleteCard(Map map) throws Exception;
	/**
	 * 更改分类
	 * @param map
	 */
	public void updateCardClassify(Map map) throws Exception;
	/**
	 * 更改icon
	 * @param map
	 */
	public void updateCardIcon(Map map) throws Exception;
	/**
	 * 查看详情
	 * @param map
	 * @return
	 */
	public Cardcase selectCardDetail(Map map) throws Exception;
}
