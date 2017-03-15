package com.wxccase.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxccase.service.UserinfoService;
import com.wxccase.utils.JsonToMap;
import com.wxccase.utils.NetReqUtil;
import com.wxccase.utils.PropertiesUtil;
import com.wxccase.utils.SnowflakeIdUtil;

@Service
public class UserinfoServiceImpl implements UserinfoService{
	
	@Resource
	private PropertiesUtil propertiesUtil;
	
	@Resource
	private SnowflakeIdUtil snowflakeIdUtil;
	
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
		String nextSession = snowflakeIdUtil.nextSession();
		map.put("session", nextSession);
		return map;
	}
	
	
}
