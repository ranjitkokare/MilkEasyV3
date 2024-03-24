package com.milkeasy.service;

import java.util.Date;
import java.util.List;

import com.milkeasy.model.MilkTransaction;


public interface MilkTransactionService{ 

	void addMilkTransaction(MilkTransaction milkTransaction);
	
	List<MilkTransaction> getAllMilkTransaction();
	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqual(Date collectionDate);

	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(
			Date fromCollectionDate, Date toCollectionDate);
	List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(
			Date fromCollectionDate, Date toCollectionDate, Long farmerId);

}
