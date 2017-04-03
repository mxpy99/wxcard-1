package com.wxccase.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {
	private static String appid ;
	private static String appsecret ;
	
	static{
		try {
			PropertiesUtil.init();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("微信配置文件异常");
		}
	}

	private static void init() throws IOException{
		Properties properties = new Properties();
		properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("wx.properties"));
		appid = (String) properties.get("appid");
		appsecret = (String) properties.get("appsecret");
	}
	public static String getAppid() {
		return appid;
	}

	public static String getAppsecret() {
		return appsecret;
	}

}
