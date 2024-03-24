package com.milkeasy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "milk_transaction")
public class MilkTransaction {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;
	
	@Column(name = "collection_date")
	private Date collectionDate; //DateTimeFormatter
	
	@Column(name = "farmer_id")
	private Long farmerId;
	
	@Column(name = "collector_id")
	private Long collectorId;
	
	private Float quantity;
	
	private int rate;
	
	private Long amount;

	
	
	public MilkTransaction() {
		super();		
	}

	public MilkTransaction(Date collectionDate, Long farmerId, Long collectorId, Float quantity, int rate, Long amount) {
		super();
		this.collectionDate = collectionDate;
		this.farmerId = farmerId;
		this.collectorId = collectorId;
		this.quantity = quantity;
		this.rate = rate;
		this.amount = amount;
	}

	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long id) {
		this.transactionId = id;
	}
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(java.sql.Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	public Long getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}
	public Long getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
	
}
