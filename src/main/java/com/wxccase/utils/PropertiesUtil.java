package com.wxccase.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {
	private static String wxappid ;
	private static String secret ;
	
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
		wxappid = (String) properties.get("wxappid");
		secret = (String) properties.get("secret");
	}
	public static String getWxappid() {
		return wxappid;
	}

	public static String getSecret() {
		return secret;
	}

}
