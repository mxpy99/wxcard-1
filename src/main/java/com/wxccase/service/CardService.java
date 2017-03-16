package com.wxccase.service;

import java.util.List;
import java.util.Map;

import com.wxccase.entity.Cardcase;

public interface CardService {
	/**
	 * 名片的修改 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map updateCard(Map map) throws Exception;
	/**
	 * 名片的新增 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map insertCard(Map map) throws Exception;
	/**
	 * 名片的删除
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map deleteCard(Map map) throws Exception;
	/**
	 * 名片的查询 分页
	 * @return
	 * @throws Exception
	 */
	public List<Cardcase> selectCard(Map map) throws Exception;
	/**
	 * 名片的详情
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Cardcase selectCardDetail(Map map) throws Exception ;
	/**
	 * 查询总数
	 * @param map
	 * @return
	 */
	public int selectCardCount(Map map) throws Exception ;
}
