package com.milkeasy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkeasy.model.MilkTransaction;

@Repository
public interface MilkTransactionRepo extends JpaRepository<MilkTransaction, Long>{
	MilkTransaction findOneByFarmerId(String farmerId);
	
	List<MilkTransaction> findByCollectionDateGreaterThanEqual(Date fromCollectionDate);
	List<MilkTransaction> findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(Date fromCollectionDate, Date toCollectionDate);
	List<MilkTransaction> findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(Date fromCollectionDate, Date toCollectionDate, Long farmerId);
	
}
