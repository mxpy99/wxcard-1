package com.wxccase.entity;

public class CardClassify {
	private String classifyid ;
	private String userid ;
	private String content ;
	
	public String getClassifyid() {
		return classifyid;
	}
	public void setClassifyid(String classifyid) {
		this.classifyid = classifyid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "CardClassify [classifyid=" + classifyid + ", userid=" + userid
				+ ", content=" + content + "]";
	}
	
}
