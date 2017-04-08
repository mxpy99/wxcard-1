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
		cardcase.setCardid(String.valueOf(map.get("cardid")));
		cardcase.setClassifyid((String.valueOf(map.get("classifyid"))));
		cardcase.setIcon(((String) map.get("icon")));
		cardcase.setUsername(String.valueOf( map.get("username")));
		cardcase.setProfession(String.valueOf( map.get("profession")));
		cardcase.setGender(String.valueOf(map.get("gender")));
		cardcase.setCarddescribe(String.valueOf( map.get("carddescribe")));
		cardcase.setCompany(String.valueOf( map.get("company")));
		cardcase.setAddress(String.valueOf( map.get("address")));
		cardcase.setQqnumber(String.valueOf( map.get("qqnumber")));
		cardcase.setWechatnumber(String.valueOf( map.get("wechatnumber")));
		cardcase.setPhonenumber(String.valueOf( map.get("phonenumber")));
		cardcase.setTelephonenumber(String.valueOf( map.get("telephonenumber")));
		cardcase.setFaxnumber(String.valueOf( map.get("faxnumber")));
		cardcase.setWebsite(String.valueOf( map.get("website")));
		cardcase.setMail(String.valueOf( map.get("mail")));
		cardcase.setCompanylogo(String.valueOf( map.get("companylogo")));
		
	    //更新
		cardcaseDaoImpl.updateCard(cardcase);
		
		//放入消息
		map.clear();
		return map;
	}

	@Override
	public Map insertCard(Map map) throws Exception {
		Cardcase cardcase = new Cardcase();
		//组装参数
		cardcase.setCardid(String.valueOf(idUtil.nextCardinfoId()));
		cardcase.setUserid(String.valueOf(map.get("userid")));
		cardcase.setClassifyid((String.valueOf(map.get("classifyid"))));
		cardcase.setIcon(((String) map.get("icon")));
		cardcase.setUsername(String.valueOf( map.get("username")));
		cardcase.setProfession(String.valueOf( map.get("profession")));
		cardcase.setGender(String.valueOf(map.get("gender")));
		cardcase.setCarddescribe(String.valueOf( map.get("carddescribe")));
		cardcase.setCompany(String.valueOf( map.get("company")));
		cardcase.setAddress(String.valueOf( map.get("address")));
		cardcase.setQqnumber(String.valueOf( map.get("qqnumber")));
		cardcase.setWechatnumber(String.valueOf( map.get("wechatnumber")));
		cardcase.setPhonenumber(String.valueOf( map.get("phonenumber")));
		cardcase.setTelephonenumber(String.valueOf( map.get("telephonenumber")));
		cardcase.setFaxnumber(String.valueOf( map.get("faxnumber")));
		cardcase.setWebsite(String.valueOf( map.get("website")));
		cardcase.setMail(String.valueOf( map.get("mail")));
		cardcase.setCompanylogo(String.valueOf( map.get("companylogo")));
	    
		cardcaseDaoImpl.insertCard(cardcase);
		
		map.clear();
		map.put("cardid", cardcase.getCardid());
		return map;
	}

	@Override
	public Map deleteCard(Map map) throws Exception {
		cardcaseDaoImpl.deleteCard(map);
		map.clear();
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
