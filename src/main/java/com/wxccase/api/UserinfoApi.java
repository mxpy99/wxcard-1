package com.wxccase.api;

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
@RequestMapping("user")
public class UserinfoApi {
	
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
		Map map = null;
		System.out.println(map);
		
		try {
			map = jsonToMap.jsonToMapUtil(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		code = (String) map.get("code");
		
		Map get3rdsession = null;
		
		try {
			get3rdsession = userinfoService.get3rdsession(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("3rd_session", (String) map.get("session_key")+(String) map.get("openid"));
		map.clear();
		return map;
	}
}
