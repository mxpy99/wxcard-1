package com.wxccase.exception.code;

import com.wxccase.exception.interfaces.MesscodeInterface;

public enum NodescribeErrorInfoEnum implements MesscodeInterface{
	NODESCRIBE_ERROR("3","不可描述的错误");
	
	private String code;
	private String message;
	
	private NodescribeErrorInfoEnum(String code,String message) {
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
