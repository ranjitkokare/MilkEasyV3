package com.milkeasy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.milkeasy.model.MilkTransaction;
import com.milkeasy.repository.MilkTransactionRepo;


@Service
public class MilkTransactionServiceImpl implements MilkTransactionService{
	
	@Autowired
	private MilkTransactionRepo milktransactionRepo;
	
	
	public MilkTransactionServiceImpl(MilkTransactionRepo milktransactionRepo ) {
		super();
		this.milktransactionRepo = milktransactionRepo;
	}
	
	@Override
	public List<MilkTransaction> getAllMilkTransaction() {
		
		return milktransactionRepo.findAll();
	}
	
	@Override
	public List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqual(Date collectionDate) {
		
		return milktransactionRepo.findByCollectionDateGreaterThanEqual(collectionDate);
	}
	
	@Override
	public List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(Date fromCollectionDate,
			Date toCollectionDate) {
		
		return milktransactionRepo.findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(fromCollectionDate,toCollectionDate);
	}
	
	@Override
	public List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(Date fromCollectionDate,
			Date toCollectionDate, Long farmerId) {
		
		return milktransactionRepo.findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(fromCollectionDate,toCollectionDate,farmerId);
	}

	@Override
	public void addMilkTransaction(MilkTransaction milkTransaction) {
		this.milktransactionRepo.save(milkTransaction);
		
	}

	
}
