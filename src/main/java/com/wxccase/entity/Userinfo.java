package com.wxccase.entity;

import java.io.Serializable;

public class Userinfo implements Serializable {
	private String userid;
	private String openid;
	private String accountname;
	private String gender;
	private String avatarurl;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAvatarurl() {
		return avatarurl;
	}
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	@Override
	public String toString() {
		return "Userinfo [userid=" + userid + ", openid=" + openid
				+ ", accountname=" + accountname + ", gender=" + gender
				+ ", avatarurl=" + avatarurl + "]";
	}
}
