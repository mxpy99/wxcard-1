package com.wxccase.service.impl;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	
	@Override
	public Map get3rdsession(String code) {
		Map map = null ;
		//请求如下链接 获取 session_key 和 openid  https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		String json = null ;
		try {
			json = netReqUtil.loadJson("https://api.weixin.qq.com/sns/jscode2session?appid="+propertiesUtil.getWxappid()+"&secret="+propertiesUtil.getSecret()+"&js_code="+code+"&grant_type=authorization_code");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			map = jsonToMap.jsonToMapUtil(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String openid = (String) map.get("openid");
		
		if(openid == null ){
			System.out.println(json);
			try {
				map = jsonToMap.jsonToMapUtil(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}
		// 生成 3rd_session 
		String nextSession = idUtil.nextSessionId();
		
		map.put("session", nextSession);
		return map;
	}
	
	
}
