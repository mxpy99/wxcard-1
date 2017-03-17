package com.wxccase.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxccase.service.UserinfoService;
import com.wxccase.utils.JsonToMap;
import com.wxccase.utils.PropertiesUtil;

@Controller
public class UserApi {
	
	@Resource
	private JsonToMap jsonToMap;

	@Resource
	private UserinfoService userinfoService;
	
	/**
	 * 微信登录
	 * @param code
	 * @return
	 */
	@RequestMapping("/onlogin")
	public @ResponseBody Map getCode(@RequestBody String code,HttpServletResponse resp,HttpServletRequest req){
		Map map = new HashMap();
		System.out.println(code);
		try {
			map = jsonToMap.jsonToMapUtil(code);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messcode", "1");
			return map;
		}
		
		code = String.valueOf( map.get("code") );
		if(code == null && "".equals(code)){
			map.put("messcode", "2");
			return map;
		}
		
		try {
			map = userinfoService.get3rdsession(code);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", "3");
			return map;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute((String) map.get("trdsession"), (String) map.get("sessionkey")+(String) map.get("openid"));
		System.out.println((String) map.get("trdsession"));
		System.out.println((String) map.get("sessionkey")+(String) map.get("openid"));

		map.put("messcode", "5");
		return map;
	}
}
