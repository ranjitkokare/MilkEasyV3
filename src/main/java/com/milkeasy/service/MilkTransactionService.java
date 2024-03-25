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
	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(
			Date fromCollectionDate, Date toCollectionDate, Long farmerId);
	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorId(
			Date fromCollectionDate, Date toCollectionDate, Long collectorId);
	List<MilkTransaction> getMilkTransactionByAdminIdAndApprovalStatus(Long adminId, String approvalStatus);
 
}
