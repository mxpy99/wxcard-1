package com.wxccase.entity;

public class Userlogin {
	private String userid;
	private String trdsession;
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
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	@Override
	public String toString() {
		return "Userlogin [userid=" + userid + ", trdsession=" + trdsession
				+ ", updatetime=" + updatetime + "]";
	}
}
