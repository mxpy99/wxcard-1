package com.wxccase.dao;

import java.util.List;
import java.util.Map;

public interface FollowDao {

	public void insertFollow(Map map) throws Exception;

	public void deleteFollow(Map map) throws Exception;

	public List<Map> listFollow(Map map) throws Exception;

	public List<Map> listUser(Map map) throws Exception;
	
}
