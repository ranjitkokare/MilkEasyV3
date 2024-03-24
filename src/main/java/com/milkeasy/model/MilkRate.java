package com.milkeasy.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "milk_rate")
public class MilkRate {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)	
	private Long id;
	
	private Date date;
	
	private Float rate;

	public MilkRate() {
		super();
		
	}

	public MilkRate(Long id, Date date, Float rate) {
		super();
		this.id = id;
		this.date = date;
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) { //Date Conversion
		this.date = date;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}
	
	
	
}
