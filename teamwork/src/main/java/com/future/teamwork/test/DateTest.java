package com.future.teamwork.test;

import java.io.Serializable;
import java.util.Date;

public class DateTest implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	
    private Date descpt;
    private Date url;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDescpt() {
		return descpt;
	}
	public void setDescpt(Date descpt) {
		this.descpt = descpt;
	}
	public Date getUrl() {
		return url;
	}
	public void setUrl(Date url) {
		this.url = url;
	}

   


}