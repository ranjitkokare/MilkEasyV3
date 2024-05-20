package com.milkeasy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	public MilkTransaction getMilkTransactionBytransactionId(Long transactionId) {
		
		return milktransactionRepo.findByTransactionId(transactionId);
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
	public List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerIdAndApprovalStatus(Date fromCollectionDate,
			Date toCollectionDate, Long farmerId, String approvalStatus) {
		
		return milktransactionRepo.findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerIdAndApprovalStatus(fromCollectionDate,toCollectionDate,farmerId, approvalStatus);
	}
	
	@Override
	public List<MilkTransaction> getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorIdAndApprovalStatus(Date fromCollectionDate,
			Date toCollectionDate, Long collectorId, String approvalStatus) {
		
		return milktransactionRepo.findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorIdAndApprovalStatus(fromCollectionDate,toCollectionDate,collectorId,approvalStatus);
	}
	
	@Override
	public List<MilkTransaction> getMilkTransactionByAdminIdAndApprovalStatus(Long adminId, String approvalStatus){
		return milktransactionRepo.findByAdminIdAndApprovalStatus(adminId,approvalStatus);
	}
	
	@Override
	public void addMilkTransaction(MilkTransaction milkTransaction) {
		
		this.milktransactionRepo.save(milkTransaction);
		
	}

	@Override
	public boolean transactionExistsForDate(Date date) {
		return milktransactionRepo.findByCollectionDate(date).isPresent();
	}
}
