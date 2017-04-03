package com.wxccase.interceptor.unit;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wxccase.dao.UserinfoDao;
import com.wxccase.entity.Userinfo;
import com.wxccase.exception.GlobalErrorInfoException;
import com.wxccase.exception.code.OpenidErrorInfoEnum;

public class TokenidIntercepter extends HandlerInterceptorAdapter {
	
	@Resource
	private UserinfoDao userinfoDaoImpl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map info = (Map) request.getAttribute("info");
		Userinfo user = userinfoDaoImpl.selectUserinfo(info);
		if(user == null){
			System.out.println("");
			throw new GlobalErrorInfoException(OpenidErrorInfoEnum.OPENID_ERROR);
		}
		System.out.println("openid"+user);
		request.setAttribute("user", user);
		return true;
	}
	
	
}
