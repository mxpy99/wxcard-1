package com.wxccase.utils;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

@Component
public class JsonToMap {

	public Map jsonToMapUtil(String str) throws Exception{
		System.out.println(str);
		
		//str = new String(str.getBytes("GBK"),"UTF-8");
		//System.out.println(str);
		Map map = JSON.parseObject(str, Map.class);
		
		return map;
	}
	
   
}
