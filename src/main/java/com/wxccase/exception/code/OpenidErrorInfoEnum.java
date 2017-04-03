package com.wxccase.exception.code;

import com.wxccase.exception.interfaces.MesscodeInterface;

public enum OpenidErrorInfoEnum implements MesscodeInterface{
	OPENID_ERROR("4","OPENID不合法");
	
	private String code;
	private String message;
	
	private OpenidErrorInfoEnum(String code,String message) {
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
