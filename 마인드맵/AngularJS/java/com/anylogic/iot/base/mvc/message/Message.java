package com.anylogic.iot.base.mvc.message;


public class Message {

	public Message(String code, String msg){
		this.setCode(code);
		this.setMsg(msg);
	};

	private String msg;

	private String code;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



}
