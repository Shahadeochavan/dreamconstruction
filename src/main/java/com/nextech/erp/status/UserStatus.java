package com.nextech.erp.status;

public class UserStatus {
	private int code;
	private String message;
//	private Object data;

	public UserStatus() {
	}

	public UserStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

//	public UserStatus(int code, Object data) {
//		this.code = code;
//		this.data = data;
//	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public Object getData() {
//		return data;
//	}
//
//	public void setData(Object data) {
//		this.data = data;
//	}
}
