package com.wxccase.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxccase.dao.FollowDao;
import com.wxccase.service.FollowService;
import com.wxccase.utils.IdUtil;

@Service
public class FollowServiceImpl implements FollowService{
	
	@Resource 
	FollowDao followDaoImpl;
	
	@Resource
	IdUtil idUtil;
	
	@Override
	public Map insertFollow(Map map) throws Exception {
		long nextFollowId = idUtil.nextFollowId();
		map.put("followid", String.valueOf(nextFollowId));
		followDaoImpl.insertFollow(map);
		map.clear();
		map.put("followid", String.valueOf(nextFollowId));
		return map;
	}
	
	@Override
	public void deleteFollow(Map map) throws Exception {
		followDaoImpl.deleteFollow(map);
	}

	@Override
	public List<Map> listFollow(Map map) throws Exception {
		List<Map> list = followDaoImpl.listFollow(map);
		return list;
	}

	@Override
	public List<Map> listUser(Map map) throws Exception {
		List<Map> list = followDaoImpl.listUser(map);
		return list;
	}

	@Override
	public Map selectByuseridAndcardid(Map map) throws Exception {
		map = followDaoImpl.selectByuseridAndcardid(map);
		return map;
	}
}
