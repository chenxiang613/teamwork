package com.future.teamwork.domain;

import java.io.Serializable;

public class ResultInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5383974114415324643L;
	int result;
	String message;
	
	public ResultInfo(int result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	
	public ResultInfo() {
		
	}
	
	public int getResult() {
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
