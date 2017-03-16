package com.wxccase.dao;

import java.util.List;
import java.util.Map;

import com.wxccase.entity.Cardcase;

public interface CardcaseDao {
	/**
	 * 新增 
	 * @param map
	 */
	public void insertCard(Cardcase cardinfo);
	/**
	 * 更新
	 * @param map
	 */
	public void updateCard(Cardcase cardinfo);
	/**
	 * 查找  需要分页
	 * @param map
	 */
	public List<Cardcase> selectCard(Map map);
	/**
	 * 查询总记录数
	 * @param map
	 */
	public String selectCardCount(Map map);
	/**
	 * 删除
	 * @param map
	 */
	public void deleteCard(Map map);
	/**
	 * 更改分类
	 * @param map
	 */
	public void updateCardClassify(Map map);
	/**
	 * 查看详情
	 * @param map
	 * @return
	 */
	public Cardcase selectCardDetail(Map map);
}
