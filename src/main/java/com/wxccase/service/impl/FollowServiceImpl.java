package com.wxccase.service.impl;

import java.util.ArrayList;
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
		List<Map> listFollow = null;
		List<String> list = followDaoImpl.listFollowHelp(map);
		if(list.size() != 0){
			for (String string : list) {
				System.out.println(string);
			}
			listFollow = followDaoImpl.listFollow(list);
		}
		return listFollow;
	}

	@Override
	public List<Map> listUser(Map map) throws Exception {
		String userid = (String) map.get("userid");
		//查询用户列表
		List<Map> listuser = followDaoImpl.listUser(map);
		List<Map> list2 = new ArrayList<Map>();
		for (Map map1 : listuser) {
			map1.put("userid", userid);
			Map map2 = followDaoImpl.selectByuseridAndcardid(map1);
			if(map2 != null ){
				System.out.println("####################");
				map1.remove("openid");
				map1.put("isconcern", "1");
			}else{
				System.out.println("******************");
				map1.remove("openid");
				map1.put("isconcern", "0");
			}
			list2.add(map1);
		}
		return list2;
	}

	@Override
	public Map selectByuseridAndcardid(Map map) throws Exception {
		map = followDaoImpl.selectByuseridAndcardid(map);
		return map;
	}
}
