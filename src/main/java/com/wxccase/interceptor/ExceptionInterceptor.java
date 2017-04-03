/**
 * viakiba
 * 2017-04-02
 * 892645423@qq.com
 */
package com.wxccase.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wxccase.exception.GlobalErrorInfoException;
import com.wxccase.exception.interfaces.MesscodeInterface;

/**
 * @author viakiba
 *
 */
@ControllerAdvice
public class ExceptionInterceptor extends HandlerInterceptorAdapter{
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public @ResponseBody Map errorHandlerOverJson(HttpServletRequest request,
                                           GlobalErrorInfoException exception) {
    	HashMap<Object, Object> map = new HashMap<>();
        MesscodeInterface errorInfo = exception.getErrorInfo();
        System.out.println(errorInfo);
        map.put("code", errorInfo.getCode());
        map.put("message", errorInfo.getMessage());
        return map;
    }
}
