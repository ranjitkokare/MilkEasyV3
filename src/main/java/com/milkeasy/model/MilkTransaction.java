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
	
	@Column(name = "farmer_fullname")
	private String farmerFullName;
	
	@Column(name = "collector_fullname")
	private String collectorFullName;
	
	@Column(name = "collector_id")
	private Long collectorId;
	
	@Column(name = "admin_fullname")
	private String adminFullName;
	
	@Column(name = "admin_id")
	private Long adminId;
	
	private Float quantity;
	
	private Float rate;
	
	private Long amount;
	
	@Column(name = "approval_status")
	private String approvalStatus;

	
	
	public MilkTransaction() {
		super();		
	}

	public MilkTransaction(Date collectionDate, String farmerFullName, String adminFullName, Float quantity, Float rate, Long amount) {
		super();
		this.collectionDate = collectionDate;
		this.farmerFullName = farmerFullName;
		this.adminFullName = adminFullName;
		this.quantity = quantity;
		this.rate = rate;
		this.amount = amount;
	}
	
	public MilkTransaction(Date collectionDate, Long farmerId, String farmerFullName, Long collectorId, String adminFullName, Float quantity, Float rate, Long amount, String approvalStatus) {
		super();
		this.collectionDate = collectionDate;
		this.farmerId = farmerId;
		this.farmerFullName = farmerFullName;
		this.collectorId = collectorId;
		this.adminFullName = adminFullName;
		this.quantity = quantity;
		this.rate = rate;
		this.amount = amount;
		this.approvalStatus = approvalStatus;
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
	public void setCollectionDate(java.sql.Date collectionDate) { //Date Conversion
		this.collectionDate = collectionDate;
	}
	public Long getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}
	public String getFarmerFullName() {
		return farmerFullName;
	}
	public void setFarmerFullName(String farmerFullName) {
		this.farmerFullName = farmerFullName;
	}
	public String getCollectorFullName() {
		return collectorFullName;
	}
	public void setCollectorFullName(String collectorFullName) {
		this.collectorFullName = collectorFullName;
	}
	public Long getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}
	public String getAdminFullName() {
		return adminFullName;
	}
	public void setAdminFullName(String adminFullName) {
		this.adminFullName = adminFullName;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	
	
}
