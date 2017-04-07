package com.wxccase.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wxccase.dao.CardClassifyDao;
import com.wxccase.dao.CardcaseDao;
import com.wxccase.dao.UserinfoDao;
import com.wxccase.entity.CardClassify;
import com.wxccase.entity.Userinfo;
import com.wxccase.service.ClassifyService;
import com.wxccase.utils.IdUtil;

@Service
public class ClassifyServiceImpl implements ClassifyService{

	@Resource
	private CardClassifyDao cardClassifyDaoImpl;

	@Resource
	private CardcaseDao cardcaseDaoImpl;
	
	@Resource
	private UserinfoDao userinfoDaoImpl;
	
	@Resource
	private IdUtil idUtil;
	
	@Override
	public Map updateClassify(Map map) throws Exception {
		cardClassifyDaoImpl.updateClassify(map);
		map.clear();
		return map;
	}

	@Override
	public Map insertClassify(Map map) throws Exception {
		long nextClassifyId = idUtil.nextClassifyId();
		map.put("classifyid", String.valueOf(nextClassifyId));
		cardClassifyDaoImpl.insertClassify(map);
		map.clear();
		map.put("classifyid", String.valueOf(nextClassifyId));
		return map;
	}

	@Override
	public Map deleteClassify(Map map) throws Exception {
		//把该分类下的名片移动到默认分类中
		String new_classifyid = (String) map.get("userid");
		String old_classifyid = (String) map.get("classifyid");
		
		map.put("new_classifyid", new_classifyid);
		map.put("old_classifyid", old_classifyid);
		
		cardcaseDaoImpl.updateCardClassify(map);
		System.out.println(map.get("classifyid"));
		cardClassifyDaoImpl.deleteClassify(map);
		map.clear();
		return map;
	}

	@Override
	public List<CardClassify> selectClassify(Map map) throws Exception {
		List<CardClassify> selectClassify = cardClassifyDaoImpl.selectClassify(map);
		return selectClassify;
	}

	@Override
	public int selectClassifyDetail(Map map) throws Exception{
		List<CardClassify> list = cardClassifyDaoImpl.selectClassifyDetail(map);
		return list.size();
	}

}
