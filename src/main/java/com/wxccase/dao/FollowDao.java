package com.wxccase.dao;

import java.util.List;
import java.util.Map;

public interface FollowDao {

	public void insertFollow(Map map) throws Exception;

	public void deleteFollow(Map map) throws Exception;

	public List<Map> listFollow(List<String> liststr) throws Exception;

	public List<Map> listUser(Map map) throws Exception;
	
	public Map selectByuseridAndcardid(Map map) throws Exception;

	List<String> listFollowHelp(Map map) throws Exception;
}
