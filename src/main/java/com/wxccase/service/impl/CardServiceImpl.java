package com.wxccase.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wxccase.dao.CardcaseDao;
import com.wxccase.entity.Cardcase;
import com.wxccase.service.CardService;
import com.wxccase.utils.IdUtil;

@Service
public class CardServiceImpl implements CardService{

	@Resource
	private CardcaseDao cardcaseDaoImpl;

	@Resource
	private IdUtil idUtil;
	
	@Override
	public Map updateCard(Map map) throws Exception {
		Cardcase cardcase = new Cardcase();
		
		//组装参数
		cardcase.setCardid(((String) map.get("cardid")));
		cardcase.setClassifyid(((String) map.get("classifyid")));
		cardcase.setIcon(((String) map.get("icon")));
		cardcase.setUserName(((String) map.get("username")));
		cardcase.setProfession(((String) map.get("profession")));
		cardcase.setIsConcern(((String) map.get("isconcern")));
		cardcase.setDescribe(((String) map.get("describe")));
		cardcase.setCompany(((String) map.get("company")));
		cardcase.setAddress(((String) map.get("address")));
		cardcase.setQqNumber(((String) map.get("qqnumber")));
		cardcase.setWechatNumber(((String) map.get("wechatnumber")));
		cardcase.setPhoneNumber(((String) map.get("phonenumber")));
		cardcase.setTelephoneNumber(((String) map.get("telephonenumber")));
		cardcase.setFaxNumber(((String) map.get("faxNumber")));
		cardcase.setWebsite(((String) map.get("website")));
		cardcase.setMail(((String) map.get("mail")));
		cardcase.setCompanyLogo(((String) map.get("companylogo")));
		
	    //更新
		cardcaseDaoImpl.updateCard(cardcase);
		
		//放入消息
		map.clear();
		map.put("messcode", 5);
		
		return map;
	}

	@Override
	public Map insertCard(Map map) throws Exception {
		Cardcase cardcase = new Cardcase();
		//组装参数
		cardcase.setCardid(String.valueOf(idUtil.nextCardinfoId()));
		cardcase.setUserid(((String) map.get("userid")));
		cardcase.setClassifyid(((String) map.get("classifyid")));
		cardcase.setIcon(((String) map.get("icon")));
		cardcase.setUserName(((String) map.get("username")));
		cardcase.setProfession(((String) map.get("profession")));
		cardcase.setIsConcern(((String) map.get("isconcern")));
		cardcase.setDescribe(((String) map.get("describe")));
		cardcase.setCompany(((String) map.get("company")));
		cardcase.setAddress(((String) map.get("address")));
		cardcase.setQqNumber(((String) map.get("qqnumber")));
		cardcase.setWechatNumber(((String) map.get("wechatnumber")));
		cardcase.setPhoneNumber(((String) map.get("phonenumber")));
		cardcase.setTelephoneNumber(((String) map.get("telephonenumber")));
		cardcase.setFaxNumber(((String) map.get("faxNumber")));
		cardcase.setWebsite(((String) map.get("website")));
		cardcase.setMail(((String) map.get("mail")));
		cardcase.setCompanyLogo(((String) map.get("companylogo")));
	    
		cardcaseDaoImpl.insertCard(cardcase);
		
		map.clear();
		map.put("messcode", 5);
		map.put("cardid", cardcase.getCardid());
		return map;
	}

	@Override
	public Map deleteCard(Map map) throws Exception {
		cardcaseDaoImpl.deleteCard(map);
		map.clear();
		map.put("messcode", 5);
		return map;
	}

	@Override
	public List<Cardcase> selectCard(Map map) throws Exception {
		List<Cardcase> list = cardcaseDaoImpl.selectCard(map);
		return list;
	}
	
	@Override
	public Cardcase selectCardDetail(Map map) throws Exception {
		Cardcase cardcase = cardcaseDaoImpl.selectCardDetail(map);
		return cardcase;
	}

	@Override
	public int selectCardCount(Map map) throws Exception {
		String selectCardCount = cardcaseDaoImpl.selectCardCount(map);
		return Integer.valueOf(selectCardCount);
	}

}
