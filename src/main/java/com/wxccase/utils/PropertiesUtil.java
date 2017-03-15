package com.wxccase.utils;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {
	private static String wxappid ;
	private static String secret ;
	
	static{
		Properties properties = new Properties();
		try {
			InputStream rs = Object.class.getClassLoader().getResourceAsStream("wx.properties");
			properties.load(rs);
			wxappid = (String) properties.get("wxappid");
			secret = (String) properties.get("secret");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("微信配置文件异常");
		}
	}

	public static String getWxappid() {
		return wxappid;
	}

	public static String getSecret() {
		return secret;
	}

}
