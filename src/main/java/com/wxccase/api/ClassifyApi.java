package com.wxccase.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxccase.dao.UserinfoDao;
import com.wxccase.entity.CardClassify;
import com.wxccase.entity.Userinfo;
import com.wxccase.exception.GlobalErrorInfoException;
import com.wxccase.exception.code.KeyvalueErrorInfoEnum;
import com.wxccase.exception.code.NodescribeErrorInfoEnum;
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
	 * @throws GlobalErrorInfoException 
	 */
	@RequestMapping("/list")
	public @ResponseBody Map lookClassifyAbs(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		String openid = String.valueOf(map.get("openid"));
		
		List<CardClassify> classifylist = null;
		
		if(openid == null || "".equals(openid) || "null".equals(openid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try {
			map.put("userid", user.getUserid());
			classifylist = classifyServiceImpl.selectClassify(map);
			for(CardClassify c : classifylist){
				System.out.println(c.toString());
			}
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("classify", classifylist);
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	/**
	 * 分类的修改
	 * @param info
	 * @return
	 * @throws GlobalErrorInfoException 
	 */
	@RequestMapping("/update")
	public @ResponseBody Map updateClassify(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		
		String openid = String.valueOf( map.get("openid") );
		String content = String.valueOf(map.get("content") );
		String classifyid = String.valueOf(map.get("classifyid"));
		
		if(openid == null || "null".equals(openid) || "".equals(openid) || classifyid == null || "null".equals(classifyid) || "".equals(classifyid) || content == null || "null".equals(content) || "null".equals(content) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try{
			map = classifyServiceImpl.updateClassify(map);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	/**
	 * 分类的删除
	 * @param info
	 * @return
	 * @throws GlobalErrorInfoException 
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map deleteClassify(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		int size = 0;
		String openid = String.valueOf(map.get("openid"));
		String classifyid = String.valueOf(map.get("content"));
		if(openid == null || "".equals(openid) || "null".equals(openid) || classifyid == null || "".equals(classifyid) || "null".equals(classifyid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try {
			map.put("userid", user.getUserid());
			System.out.println(user.getUserid()+"================");
			System.out.println(map.get("classifyid")+"=============");
			if(user.getUserid().equals(((String) map.get("classifyid")))){
				System.out.println("-1");
				map.clear();
				map.put("code", "0");
				map.put("message", "operation success");
				return map;
			}
		    size = classifyServiceImpl.selectClassifyDetail(map);
		} catch (Exception e1) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		
		if(size == 0){
			map.clear();
			map.put("code", 6);
			map.put("message", "classifid not find");
			return map;
		}
		
		try {
			map = classifyServiceImpl.deleteClassify(map);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("code", 1);
		map.put("message", "operation success");
		return map;
	}
	
	/**
	 * 分类的新增
	 * @param info
	 * @return
	 * @throws GlobalErrorInfoException 
	 */
	@RequestMapping("/insert")
	public @ResponseBody Map insertClassify(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		
		int size = 0;
		
		String openid = String.valueOf(map.get("openid"));
		String content = (String) map.get("content");
		
		if(openid == null || "".equals(openid) || "null".equals(openid) || content == null || "".equals(content) || "null".equals(content) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try {
			map.put("userid", user.getUserid());
		    size = classifyServiceImpl.selectClassifyDetail(map);
		} catch (Exception e1) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		
		if(size != 0){
			map.put("code", "6");
			map.put("message", "添加失败,已经存在，或者不符合规范");
			return map;
		}
		
		try {
			System.out.println(map.get("userid"));
			System.out.println(map.get("content"));
			map = classifyServiceImpl.insertClassify(map);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("code", 5);
		map.put("message", 1);
		return map;
	}
	
}
