package com.milkeasy.service;

import java.util.Date;
import java.util.List;

import com.milkeasy.model.MilkTransaction;


public interface MilkTransactionService{ 

	void addMilkTransaction(MilkTransaction milkTransaction);
	
	//void setMilkTransactionBytransactionId(Long transactionId);
	
	List<MilkTransaction> getAllMilkTransaction();
	MilkTransaction getMilkTransactionBytransactionId(Long transactionId);
	
	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqual(Date collectionDate);

	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(
			Date fromCollectionDate, Date toCollectionDate);
	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerIdAndApprovalStatus(
			Date fromCollectionDate, Date toCollectionDate, Long farmerId, String approvalStatus);
	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorIdAndApprovalStatus(
			Date fromCollectionDate, Date toCollectionDate, Long collectorId, String approvalStatus);
	List<MilkTransaction> getMilkTransactionByAdminIdAndApprovalStatus(Long adminId, String approvalStatus);
 
	boolean transactionExistsForDate(Date date);
}
