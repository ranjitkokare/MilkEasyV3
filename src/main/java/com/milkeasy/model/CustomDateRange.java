package com.milkeasy.model;

import java.util.Date;

import javax.persistence.Entity;

public class CustomDateRange {
	
	private Date customFromdate;
	private Date customTodate;
	
	public Date getCustomFromdate() {
		return customFromdate;
	}
	public void setCustomFromdate(java.sql.Date customFromdate) {
		this.customFromdate = customFromdate;
	}
	public Date getCustomTodate() {
		return customTodate;
	}
	public void setCustomTodate(java.sql.Date customTodate) {
		this.customTodate = customTodate;
	}
	
	public CustomDateRange() {
		super();		
	}
}
