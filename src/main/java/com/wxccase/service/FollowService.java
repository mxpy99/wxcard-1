package com.wxccase.service;

import java.util.List;
import java.util.Map;

public interface FollowService {

	public Map insertFollow(Map map) throws Exception;

	public void deleteFollow(Map map) throws Exception;

	public List<Map> listFollow(Map map) throws Exception;

	public List<Map> listUser(Map map) throws Exception;

}
