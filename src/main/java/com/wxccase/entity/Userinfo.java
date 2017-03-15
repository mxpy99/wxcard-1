package com.wxccase.entity;

public class Userinfo {
	private String userid;
	private String wxid;
	private String accountname;
	private String gender;
	private String avatarurl;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWxid() {
		return wxid;
	}
	public void setWxid(String wxid) {
		this.wxid = wxid;
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
		return "Userinfo [userid=" + userid + ", wxid=" + wxid
				+ ", accountname=" + accountname + ", gender=" + gender
				+ ", avatarurl=" + avatarurl + "]";
	}
}
