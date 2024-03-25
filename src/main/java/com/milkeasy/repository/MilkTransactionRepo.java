package com.milkeasy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.milkeasy.model.MilkTransaction;

@Repository
public interface MilkTransactionRepo extends JpaRepository<MilkTransaction, Long>{
	MilkTransaction findOneByFarmerId(String farmerId);
	
	List<MilkTransaction> findByCollectionDateGreaterThanEqual(Date fromCollectionDate);
	List<MilkTransaction> findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(Date fromCollectionDate, Date toCollectionDate);
	List<MilkTransaction> findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(Date fromCollectionDate, Date toCollectionDate, Long farmerId);
	List<MilkTransaction> findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorId(Date fromCollectionDate, Date toCollectionDate, Long collectorId);
	List<MilkTransaction> findByAdminIdAndApprovalStatus(Long adminId, String approvalStatus);
	
	@Query("SELECT SUM(t.amount) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.farmerId = :farmerId")
    Double getTotalAmountByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("farmerId") Long farmerId);

	@Query("SELECT SUM(t.quantity) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.farmerId = :farmerId")
	Double getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("farmerId") Long farmerId);
	
	@Query("SELECT SUM(t.amount) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.collectorId = :collectorId")
 Double getTotalAmountByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorId(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("collectorId") Long collectorId);

	@Query("SELECT SUM(t.quantity) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.collectorId = :collectorId")
	Double getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorId(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("collectorId") Long collectorId);

}
