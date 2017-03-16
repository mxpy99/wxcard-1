package com.wxccase.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxccase.dao.UserinfoDao;
import com.wxccase.entity.CardClassify;
import com.wxccase.entity.Userinfo;
import com.wxccase.service.ClassifyService;
import com.wxccase.utils.JsonToMap;

@Controller
@RequestMapping("/classify")
public class ClassifyApi {
	
	@Resource
	private JsonToMap jsonToMap;
	
	@Resource
	private ClassifyService classifyServiceImpl;
	
	@Resource
	private UserinfoDao userinfoDaoImpl;
	
	/**
	 * 分类列表查看
	 * @param info
	 * @return
	 */
	@RequestMapping("/list")
	public @ResponseBody Map lookClassifyAbs(@RequestBody String info){
		Map map = null;
		String openid = null;
		List<CardClassify> classifylist = null;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		
		if(openid == null || "".equals(openid) ){
			map.clear();
			map.put("messcode", 2);
			return map;
		}
		
		Userinfo user = userinfoDaoImpl.selectUserinfo(map);
		if(user.getUserid() == null){
			map.clear();
			map.put("messcode", 4);
			return map;
		}
		
		try {
			map.put("userid", user.getUserid());
			classifylist = classifyServiceImpl.selectClassify(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		map.put("classify", classifylist);
		map.put("messcode", 5);
		return map;
	}
	
	/**
	 * 分类的修改
	 * @param info
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Map updateClassify(@RequestBody String info){
		Map map = null;
		String openid = null;
		String classifyid = null;
		String content = null;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		classifyid = (String) map.get("content");
		classifyid = (String) map.get("content");
		
		if(openid == null || "".equals(openid) || classifyid == null || "".equals(classifyid) || content == null || "".equals(content) ){
			map.clear();
			map.put("messcode", 2);
			return map;
		}
		
		Userinfo user = userinfoDaoImpl.selectUserinfo(map);
		if(user.getUserid() == null){
			map.clear();
			map.put("messcode", 4);
			return map;
		}
		
		try{
			map = classifyServiceImpl.updateClassify(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		
		return map;
	}
	
	/**
	 * 分类的删除
	 * @param info
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map deleteClassify(@RequestBody String info){
		Map map = null;
		String openid = null;
		String classifyid = null;
		Userinfo user = null;
		int size = 0;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		classifyid = (String) map.get("content");
		
		if(openid == null || "".equals(openid) || classifyid == null || "".equals(classifyid) ){
			map.clear();
			map.put("messcode", 2);
			return map;
		}
		
		try {
			user = userinfoDaoImpl.selectUserinfo(map);
			map.put("userid", user.getUserid());
			if(user.getUserid() == ((String) map.get("classifyid")).trim()){
				map.clear();
				map.put("messcode", 5);
				map.put("success", -1);
				return map;
			}
		    size = classifyServiceImpl.selectClassifyDetail(map);
		} catch (Exception e1) {
			e1.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		
		if(size == 0){
			map.clear();
			map.put("messcode", 4);
			return map;
		}
		
		try {
			classifyServiceImpl.deleteClassify(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		return map;
	}
	
	/**
	 * 分类的新增
	 * @param info
	 * @return
	 */
	@RequestMapping("/insert")
	public @ResponseBody Map insertClassify(@RequestBody String info){
		Map map = null;
		String openid = null;
		String classify = null;
		Userinfo user = null;
		int size = 0;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		classify = (String) map.get("content");
		
		if(openid == null || "".equals(openid) || classify == null || "".equals(classify) ){
			map.clear();
			map.put("messcode", 2);
			return map;
		}
		
		try {
			user = userinfoDaoImpl.selectUserinfo(map);
			map.put("userid", user.getUserid());
		    size = classifyServiceImpl.selectClassifyDetail(map);
		} catch (Exception e1) {
			e1.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		
		if(size != 0){
			map.put("messcode", 5);
			map.put("success", -1);
			return map;
		}
		
		try {
			map = classifyServiceImpl.insertClassify(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		return map;
	}
	
}
