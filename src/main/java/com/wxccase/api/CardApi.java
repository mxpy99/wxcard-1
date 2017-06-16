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
import com.wxccase.entity.Cardcase;
import com.wxccase.entity.Userinfo;
import com.wxccase.exception.GlobalErrorInfoException;
import com.wxccase.exception.code.KeyvalueErrorInfoEnum;
import com.wxccase.exception.code.NodescribeErrorInfoEnum;
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
	public @ResponseBody Map lookCardAbs(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		List<Cardcase> list = null;
		
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		
		String openid = String.valueOf(map.get("openid"));
		String pagenum = String.valueOf(map.get("pagenum"));
		String pagesize = String.valueOf(map.get("pagesize"));
		
		if(pagesize == null || "".equals(pagesize) || "null".equals(pagesize) || pagenum == null || "".equals(pagenum) || "null".equals(pagenum) || openid == null || "".equals(openid) || "null".equals(openid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
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
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		
		map.clear();
		map.put("code", "0");
		map.put("message", "operation success");
		map.put("cardlist", list);
		return map;
	}
	
	//名片详情查看
	@RequestMapping("/detail")
	public @ResponseBody Map lookCardDetail(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		Cardcase cardDetail = null;
		
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		String openid = String.valueOf(map.get("openid"));
		if( openid == null || "".equals(openid)|| "null".equals(openid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		map.put("cardid", user.getUserid());
		try {
			 cardDetail = cardServiceImpl.selectCardDetail(map);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.clear();
		map.put("code", "0");
		map.put("message", "operation success");
		map.put("cardDetail", cardDetail);
		return map;
	}
	
	/**
	 * 名片的修改
	 * @param info
	 * @return
	 * @throws GlobalErrorInfoException 
	 */
	@RequestMapping("/update")
	public @ResponseBody Map updatecard(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		
		String openid = String.valueOf( map.get("openid"));
		String classifyid = String.valueOf(map.get("classifyid"));
		
		if( openid == null || "".equals(openid) || "null".equals(openid) || classifyid == null || "".equals(classifyid) || "null".equals(classifyid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try {
			map.put("cardid", user.getUserid());
			map = cardServiceImpl.updateCard(map);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	/**
	 * 名片的删除
	 * @param info
	 * @return
	 * @throws GlobalErrorInfoException 
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map deleteCard(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		String openid = String.valueOf(map.get("openid"));
		String cardid = String.valueOf( map.get("cardid"));
		
		if(openid == null || "".equals(openid) || "null".equals(openid) || cardid == null || "".equals(cardid) || "null".equals(cardid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try {
			map = cardServiceImpl.deleteCard(map);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	/**
	 * 名片的新增
	 * @param info
	 * @return
	 * @throws GlobalErrorInfoException 
	 */
	@RequestMapping("/insert")
	public @ResponseBody Map insertCard(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		
		String openid = String.valueOf(map.get("openid"));
		String classifyid = String.valueOf( map.get("classifyid"));
		
		if(openid == null || "".equals(openid) || "null".equals(openid) || classifyid == null || "".equals(classifyid)  || "null".equals(classifyid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		map.put("userid", user.getUserid());
		
		try {
			map = cardServiceImpl.insertCard(map);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	
}
