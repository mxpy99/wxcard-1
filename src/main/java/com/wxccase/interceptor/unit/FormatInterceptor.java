package com.wxccase.interceptor.unit;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wxccase.exception.GlobalErrorInfoException;
import com.wxccase.exception.code.FormatErrorInfoEnum;
import com.wxccase.utils.JsonRequestWrapper;
import com.wxccase.utils.JsonToMap;

public class FormatInterceptor extends HandlerInterceptorAdapter{
	
	@Resource
	private JsonToMap jsonToMap;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		JsonRequestWrapper myJsonRequestWrapper = new JsonRequestWrapper(request);
		Map info = null;
		try{
			info = jsonToMap.jsonToMapUtil(myJsonRequestWrapper.getBody());
		}catch(Exception e){
			throw new GlobalErrorInfoException(FormatErrorInfoEnum.FORMAT_ERROR);
		}
		myJsonRequestWrapper.setAttribute("info", info);
		return true;
	}
}
