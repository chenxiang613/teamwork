package com.future.teamwork.domain;

public class ResultInfo {
	int result;
	String message;
	
	public ResultInfo(int result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	public int isResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
