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
	public @ResponseBody Map getCode(HttpServletResponse resp,HttpServletRequest req){
		String code = (String) req.getParameter("code");
		Map map = new HashMap();
		System.out.println(code);
		
		HttpSession session = req.getSession();
		String trd_session = (String) session.getAttribute("3rd_session");
		System.out.println(trd_session);
		//先进行判断是否为空
		if(trd_session == null){
			//为空
			
		}else{
			//不为空 此时上数据库检查  3rd_session 时间是否失效
			
			
			if(0 == 0){
				//失效
				
			}else{
				//有效
				
			}
			
		}
		
		session.setAttribute("3rd_session", (String) map.get("session_key")+(String) map.get("openid")+"test");
		map.clear();
		map.put("s", "sdas");
		return map;
	}
}
