package com.wxccase.exception.code;

import com.wxccase.exception.interfaces.MesscodeInterface;

public enum FormatErrorInfoEnum implements MesscodeInterface{
	FORMAT_ERROR("1","格式不正确");
	
	private String code;
	private String message;
	
	private FormatErrorInfoEnum(String code,String message) {
		this.code = code;
		this.message = message;
	}
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
