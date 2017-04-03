package com.wxccase.exception.code;

import com.wxccase.exception.interfaces.MesscodeInterface;

public enum KeyvalueErrorInfoEnum implements MesscodeInterface{
	KEYVALUE_ERROR("2","键值对不符合规范");
	
	private String code;
	private String message;
	
	private KeyvalueErrorInfoEnum(String code,String message) {
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
