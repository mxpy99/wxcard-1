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
import com.wxccase.entity.Cardcase;
import com.wxccase.entity.Userinfo;
import com.wxccase.service.CardService;
import com.wxccase.utils.JsonToMap;

@Controller
@RequestMapping("/card")
public class CardApi {
	
	@Resource
	private JsonToMap jsonToMap;
	
	@Resource
	private UserinfoDao userinfoDaoImpl;
	
	@Resource
	private CardService cardServiceImpl;
	
    //名片列表查看
	@RequestMapping("/list")
	public @ResponseBody Map lookCardAbs(@RequestBody String info){
		Map map = null;
		
		String openid = null;
		String pagenum = null;
		String pagesize = null;
		List<Cardcase> list = null;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		pagenum = (String) map.get("pagenum");
		pagesize = (String) map.get("pagesize");
		
		if(pagesize == null || "".equals(pagesize) || pagenum == null || "".equals(pagenum) || openid == null || "".equals(openid) ){
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
		
		map.put("userid", user.getUserid());
		
		try {
			String classifyid = (String) map.get("classifyid");
			map.clear();
			map.put("classifyid", classifyid);
			map.put("userid", user.getUserid());
			int i = cardServiceImpl.selectCardCount(map);//总数
			//起始页数    页面大小
			int start = Integer.valueOf(pagenum) * Integer.valueOf(pagesize);
			map.put("start", start);
			map.put("size", Integer.valueOf(pagesize));
			list = cardServiceImpl.selectCard(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		map.clear();
		map.put("messcode", 5);
		map.put("cardlist", list);
		return map;
	}
	
	//名片详情查看
	@RequestMapping("/detail")
	public @ResponseBody Map lookCardDetail(@RequestBody String info){
		Map map = null;
		String openid = null;
		String cardid = null;
		Cardcase cardDetail = null;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		cardid = (String) map.get("classifyid");
		
		if(cardid == null || "".equals(cardid) || openid == null || "".equals(openid) ){
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
			 cardDetail = cardServiceImpl.selectCardDetail(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		map.put("messcode", 5);
		map.put("cardDetail", cardDetail);
		return map;
	}
	
	/**
	 * 名片的修改
	 * @param info
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Map updatecard(@RequestBody String info){
		Map map = null;
		String openid = null;
		String classifyid = null;
		String cardid = null;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		classifyid = (String) map.get("classifyid");
		cardid = (String) map.get("classifyid");
		
		if(cardid == null || "".equals(cardid) || openid == null || "".equals(openid) || classifyid == null || "".equals(classifyid) ){
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
			map = cardServiceImpl.updateCard(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		
		return map;
	}
	
	/**
	 * 名片的删除
	 * @param info
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map deleteCard(@RequestBody String info){
		Map map = null;
		String openid = null;
		String cardid = null;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		cardid = (String) map.get("cardid");
		
		if(openid == null || "".equals(openid) || cardid == null || "".equals(cardid) ){
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
			map = cardServiceImpl.deleteCard(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		
		return map;
	}
	
	/**
	 * 名片的新增
	 * @param info
	 * @return
	 */
	@RequestMapping("/insert")
	public @ResponseBody Map insertCard(@RequestBody String info){
		Map map = null;
		
		String openid = null;
		String classifyid = null;
		
		try {
			map = jsonToMap.jsonToMapUtil(info);
		} catch (Exception e) {
			e.printStackTrace();
			map = new HashMap();
			map.put("messcode", 1);
			return map;
		}
		
		openid = (String) map.get("openid");
		classifyid = (String) map.get("classifyid");
		
		if(openid == null || "".equals(openid) || classifyid == null || "".equals(classifyid) ){
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
		map.put("userid", user.getUserid());
		
		try {
			map = cardServiceImpl.insertCard(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("messcode", 3);
			return map;
		}
		
		return map;
	}
	
	
}
