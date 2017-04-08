package com.wxccase.entity;

import java.io.Serializable;

public class Cardcase implements Serializable{
	private String cardid ;
	private String userid ;
	private String classifyid ;
	private String icon;
	private String username;
	private String profession;
	private String gender;
	private String carddescribe;
	private String company;
	private String address;
	private String qqnumber;
	private String wechatnumber;
	private String phonenumber;
	private String telephonenumber;
	private String faxnumber;
	private String website;
	private String mail;
	private String companylogo;
	
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getClassifyid() {
		return classifyid;
	}
	public void setClassifyid(String classifyid) {
		this.classifyid = classifyid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCarddescribe() {
		return carddescribe;
	}
	public void setCarddescribe(String carddescribe) {
		this.carddescribe = carddescribe;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQqnumber() {
		return qqnumber;
	}
	public void setQqnumber(String qqnumber) {
		this.qqnumber = qqnumber;
	}
	public String getWechatnumber() {
		return wechatnumber;
	}
	public void setWechatnumber(String wechatnumber) {
		this.wechatnumber = wechatnumber;
	}
	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}
	public String getTelephonenumber() {
		return telephonenumber;
	}
	public String getCompanylogo() {
		return companylogo;
	}
	public void setCompanylogo(String companylogo) {
		this.companylogo = companylogo;
	}
	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}
	public String getFaxnumber() {
		return faxnumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "Cardcase [cardid=" + cardid + ", userid=" + userid
				+ ", classifyid=" + classifyid + ", icon=" + icon
				+ ", username=" + username + ", profession=" + profession
				+ ", isconcern=" + gender + ", carddescribe=" + carddescribe
				+ ", company=" + company + ", address=" + address
				+ ", qqnumber=" + qqnumber + ", wechatnumber=" + wechatnumber
				+ ", phonenumber=" + phonenumber + ", telephonenumber="
				+ telephonenumber + ", faxnumber=" + faxnumber + ", website="
				+ website + ", mail=" + mail + ", companylogo=" + companylogo
				+ "]";
	}
}
