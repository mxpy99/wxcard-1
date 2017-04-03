package com.wxccase.service;

import java.util.Map;

public interface UserinfoService {
	
	public Map get3rdsession(String code) throws Exception ;
	
	public void updateLogo(Map map) throws Exception;

	public void insertFaq(Map map)  throws Exception;
	
}
