package com.wxccase.entity;

import java.io.Serializable;

public class Userlogin implements Serializable {
	private String userid;
	private String trdsession;
	private String sessionkey;
	private String updatetime;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTrdsession() {
		return trdsession;
	}
	public void setTrdsession(String trdsession) {
		this.trdsession = trdsession;
	}
	public String getSessionkey() {
		return sessionkey;
	}
	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	@Override
	public String toString() {
		return "Userlogin [userid=" + userid + ", trdsession=" + trdsession
				+ ", sessionkey=" + sessionkey + ", updatetime=" + updatetime
				+ "]";
	}
}
