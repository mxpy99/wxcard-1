package com.wxccase.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxccase.dao.CardcaseDao;
import com.wxccase.dao.UserinfoDao;
import com.wxccase.dao.UserloginDao;
import com.wxccase.entity.Cardcase;
import com.wxccase.entity.Userinfo;
import com.wxccase.service.UserinfoService;
import com.wxccase.utils.IdUtil;
import com.wxccase.utils.JsonToMap;
import com.wxccase.utils.NetReqUtil;
import com.wxccase.utils.PropertiesUtil;

@Service
public class UserinfoServiceImpl implements UserinfoService{
	
	@Resource
	private PropertiesUtil propertiesUtil;
	
	@Resource
	private IdUtil idUtil;
	
	@Resource
	private NetReqUtil netReqUtil;
	
	@Resource
	private JsonToMap jsonToMap;
	
	@Resource
	private UserinfoDao userinfoDao;
	
	@Resource
	private UserloginDao userloginDao;
	
	@Resource
	private CardcaseDao cardcaseDao;
	
	@Override
	public Map get3rdsession(String code) throws Exception{
		Map map = null ;
		String json = null ;
		String nextSession = null;
		long nextUserId = 1L;
		String session_key = null;
		Userinfo user = null;
		String openid = null;
		
		try {
			//请求如下链接 获取 session_key 和 openid  https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
			System.out.println(propertiesUtil.getAppid()+"==="+propertiesUtil.getAppsecret());
			json = netReqUtil.loadJson("https://api.weixin.qq.com/sns/jscode2session?appid="+propertiesUtil.getAppid()+"&secret="+propertiesUtil.getAppsecret()+"&js_code="+code+"&grant_type=authorization_code");
			//json = "{\"openid\":111,\"session_key\":1}";
			System.out.println(json);
		} catch (Exception e1) {
			e1.printStackTrace();
			map = new HashMap();
			map.put("messcode", "4");
			return map;
		}
		
		try {
			map = jsonToMap.jsonToMapUtil(json);
			openid = String.valueOf( map.get("openid") );
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", "4");
			return map;
		}
		
		if(openid == null){
			map.clear();
			map.put("messcode", json);
			return map;
		}
		
		session_key = String.valueOf( map.get("session_key") );
		
		//查询此用户是否存在
		user = userinfoDao.selectUserinfo(map);
		if(user == null){
			//新增用户
			nextUserId = idUtil.nextUserId();
			map.clear();
			map.put("openid", openid);
			map.put("userid", String.valueOf(nextUserId));
			userinfoDao.insertUserinfo(map);
			//新增cardcase
			Cardcase cardcase = new Cardcase();
			cardcase.setCardid(String.valueOf(nextUserId));
			cardcase.setUserid(String.valueOf(nextUserId));
			cardcaseDao.insertCard(cardcase);
			//新增userlogin 记录
			nextSession = idUtil.nextSessionId();
			map.remove("openid");
			map.put("trdsession", nextSession);
			map.put("sessionkey", session_key);
			userloginDao.insertUserlogin(map);
			map.clear();
			map.put("isnewuser", "1");
			map.put("userid", String.valueOf(nextUserId));
		}else{
			//更新用户 userlogin
			map.clear();
			nextSession = idUtil.nextSessionId();
			map.put("trdsession", nextSession);
			map.put("sessionkey", session_key);
			map.put("userid", user.getUserid());
			userloginDao.updateUserlogin(map);
			map.clear();
			map.put("isnewuser", "0");
			map.put("userid", user.getUserid());
		}
		
		map.put("trdsession", nextSession);
		map.put("sessionkey", session_key);
		map.put("openid", openid);
		return map;
	}
	
	@Override
	public void updateLogo(Map map) throws Exception{
		userinfoDao.updateLogo(map);
	}
	
	@Override
	public void insertFaq(Map map) throws Exception {
		long nextFaqId = idUtil.nextFaqId();
		map.put("faqid", String.valueOf(nextFaqId));
		userinfoDao.insertFaq(map);
	}
}
