package com.wxccase.api;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wxccase.dao.UserinfoDao;
import com.wxccase.entity.Userinfo;
import com.wxccase.exception.GlobalErrorInfoException;
import com.wxccase.exception.code.KeyvalueErrorInfoEnum;
import com.wxccase.exception.code.NodescribeErrorInfoEnum;
import com.wxccase.exception.code.OpenidErrorInfoEnum;
import com.wxccase.service.FollowService;
import com.wxccase.service.UserinfoService;
import com.wxccase.utils.JsonToMap;

@Controller
public class UserApi {
	
	@Resource
	private JsonToMap jsonToMap;

	@Resource
	private UserinfoService userinfoService;
	
	@Resource
	private UserinfoDao userinfoDaoImpl;
	
	@Resource
	private FollowService followServiceImpl;
	
	/**
	 * 微信登录
	 * @param code
	 * @return
	 * @throws GlobalErrorInfoException 
	 */
	@RequestMapping("/onlogin")
	public @ResponseBody Map getCode(HttpServletResponse resp,HttpServletRequest req) throws GlobalErrorInfoException{
		Map info = (Map) req.getAttribute("info");
		
		String code = String.valueOf( info.get("code") );
		if(code == null || "".equals(code) || "null".equals(code)){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try {
			info = userinfoService.get3rdsession(code);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		
		HttpSession session = req.getSession();
		session.setAttribute((String) info.get("trdsession"), (String) info.get("sessionkey")+(String) info.get("openid"));
		System.out.println((String) info.get("trdsession"));
		System.out.println((String) info.get("sessionkey")+(String) info.get("openid"));
		info.put("code", "0");
		info.put("message", "operation success");
		return info;
	}
	
	/**
	 * 上传logo
	 * @param openid
	 * @param file
	 * @param resp
	 * @param req
	 * @return
	 * @throws Exception
	 * @author viakiba
	 * @time 2017-04-04
	 * 892645423@qq.com
	 */
	@RequestMapping("/extra/uploadlogo/{openid}")
	private @ResponseBody Map upLoadLogo(@PathVariable String openid,@RequestParam MultipartFile file,HttpServletResponse resp,HttpServletRequest req) throws Exception{
		System.out.println(openid);
		Map map = new HashMap<>();
		map.put("openid", openid.trim());
		Userinfo user = userinfoDaoImpl.selectUserinfo(map);
		
		if(user == null){
			throw new GlobalErrorInfoException(OpenidErrorInfoEnum.OPENID_ERROR);
		}
		
		String name = file.getOriginalFilename();
		String companylogo = name.substring(name.lastIndexOf("."));
		System.out.println(companylogo);
		String path = "d:/wxcard/images/"+openid+companylogo;  
		File files = new File(path);
		file.transferTo(files);
		
		try{
			map.clear();
			map.put("userid", user.getUserid());
			map.put("companylogo", companylogo);
			userinfoService.updateLogo(map);
		}catch(Exception e){
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	@RequestMapping("/insertfollow")
	private @ResponseBody Map insertFollow(HttpServletResponse resp,HttpServletRequest req) throws Exception{
		
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		
		String openid = (String) map.get("openid");
		String cardid = (String) map.get("cardid");
		
		if(openid == null || "".equals(openid) || "null".equals(openid) || cardid == null || "".equals(cardid) || "null".equals(cardid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		map.put("userid", user.getUserid());
		
		try{
			map = followServiceImpl.insertFollow(map);
		}catch(Exception e){
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	@RequestMapping("/deletefollow")
	private @ResponseBody Map deleteFollow(HttpServletResponse resp,HttpServletRequest req) throws Exception{
		
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		
		String openid = (String) map.get("openid");
		String cardid = (String) map.get("email");
		
		if(openid == null || "".equals(openid) || "null".equals(openid) || cardid == null || "".equals(cardid) || "null".equals(cardid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		map.put("userid", user.getUserid());
		try{
			followServiceImpl.deleteFollow(map);
		}catch(Exception e){
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.clear();
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
		
	}
	
	/**
	 * 我的关注的名片
	 * @param resp
	 * @param req
	 * @return
	 * @throws Exception
	 * @author viakiba
	 * @time 2017-04-04
	 * 892645423@qq.com
	 */
	@RequestMapping("/listFollow")
	private @ResponseBody Map listFollow(HttpServletResponse resp,HttpServletRequest req) throws Exception{
		
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		List<Map> list = null;
				
		String openid = (String) map.get("openid");
		String pagenum = String.valueOf(map.get("pagenum"));
		String pagesize = String.valueOf(map.get("pagesize"));
		
		if(pagesize == null || "".equals(pagesize) || "null".equals(pagesize) || pagenum == null || "".equals(pagenum) || "null".equals(pagenum) || openid == null || "".equals(openid) || "null".equals(openid) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try{
			map.clear();
			map.put("userid", user.getUserid());
			//起始页数    页面大小
			int start = Integer.valueOf(pagenum) * Integer.valueOf(pagesize);
			map.put("start", start);
			map.put("size", Integer.valueOf(pagesize));
			list = followServiceImpl.listFollow(map);
		}catch(Exception e){
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.clear();
		map.put("code", "0");
		map.put("message", "operation success");
		map.put("cardlist", list);
		return map;
	}
	
	/**
	 * 
	 * @param resp
	 * @param req
	 * @return
	 * @throws Exception
	 * @author viakiba
	 * @time 2017-04-04
	 * 892645423@qq.com
	 */
	@RequestMapping("/listUser")
	private @ResponseBody Map listUser(HttpServletResponse resp,HttpServletRequest req) throws Exception{
		
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		List<Map> list = null;
				
		String pagenum = String.valueOf(map.get("pagenum"));
		String pagesize = String.valueOf(map.get("pagesize"));
		String keywords = String.valueOf(map.get("keywords"));
		
		if(pagesize == null || "".equals(pagesize) || "null".equals(pagesize) || pagenum == null || "".equals(pagenum) || "null".equals(pagenum) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		try{
			map.clear();
			//起始页数    页面大小
			int start = Integer.valueOf(pagenum) * Integer.valueOf(pagesize);
			map.put("start", start);
			map.put("size", Integer.valueOf(pagesize));
			map.put("keywords", keywords);
			list = followServiceImpl.listUser(map);
		}catch(Exception e){
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.clear();
		map.put("code", "0");
		map.put("message", "operation success");
		map.put("cardlist", list);
		return map;
	}
	
	@RequestMapping("/insertfaq")
	private @ResponseBody Map faqWxcard(HttpServletResponse resp,HttpServletRequest req) throws Exception{
		Map map = (Map) req.getAttribute("info");
		Userinfo user = (Userinfo) req.getAttribute("user");
		
		String openid = (String) map.get("openid");
		String email = (String) map.get("email");
		String description = (String) map.get("description");
		
		if(openid == null || "".equals(openid) || "null".equals(openid) || email == null || "".equals(email) || "null".equals(email) || description == null || "".equals(description) || "null".equals(description) ){
			throw new GlobalErrorInfoException(KeyvalueErrorInfoEnum.KEYVALUE_ERROR);
		}
		
		map.put("userid", user.getUserid());
		try{
			userinfoService.insertFaq(map);
		}catch(Exception e){
			throw new GlobalErrorInfoException(NodescribeErrorInfoEnum.NODESCRIBE_ERROR);
		}
		map.clear();
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	@RequestMapping("/test")
	private @ResponseBody Map test(HttpServletResponse resp,HttpServletRequest req) throws Exception{
		Map map = new HashMap<>();
		map.put("code", "0");
		map.put("message", "operation success");
		return map;
	}
	
	
}
