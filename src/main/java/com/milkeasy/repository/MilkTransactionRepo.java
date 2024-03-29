package com.milkeasy.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.milkeasy.model.MilkTransaction;

@Repository
public interface MilkTransactionRepo extends JpaRepository<MilkTransaction, Long>{
	MilkTransaction findOneByFarmerId(String farmerId);
	
	List<MilkTransaction> findByCollectionDateGreaterThanEqual(Date fromCollectionDate);
	MilkTransaction findByTransactionId(Long transactionid);
	List<MilkTransaction> findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(Date fromCollectionDate, Date toCollectionDate);
	List<MilkTransaction> findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerIdAndApprovalStatus(Date fromCollectionDate, Date toCollectionDate, Long farmerId, String approvalStatus);
	List<MilkTransaction> findByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorIdAndApprovalStatus(Date fromCollectionDate, Date toCollectionDate, Long collectorId, String approvalStatus);
	List<MilkTransaction> findByAdminIdAndApprovalStatus(Long adminId, String approvalStatus);
	
	
	@Query("SELECT SUM(t.amount) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.farmerId = :farmerId AND t.approvalStatus = 'approved'")
    Double getTotalAmountByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("farmerId") Long farmerId);

	@Query("SELECT SUM(t.quantity) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.farmerId = :farmerId AND t.approvalStatus = 'approved'")
	Double getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("farmerId") Long farmerId);
	
	@Query("SELECT SUM(t.amount) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.collectorId = :collectorId AND t.approvalStatus = 'approved'")
	Double getTotalAmountByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorId(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("collectorId") Long collectorId);

	@Query("SELECT SUM(t.quantity) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.collectorId = :collectorId AND t.approvalStatus = 'approved'")
	Double getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorId(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("collectorId") Long collectorId);
	//total approved collection by admin
	@Query("SELECT SUM(t.quantity) FROM MilkTransaction t " +
	           "WHERE t.collectionDate BETWEEN :startDate AND :endDate " +
	           "AND t.adminId = :adminId AND t.approvalStatus = :approvalStatus")
	Double getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndAdminIdAndApprovalStatus(@Param("startDate") Date fromCollectionDate,
	                                                 @Param("endDate") Date toCollectionDate,
	                                                 @Param("adminId") Long adminId, @Param("approvalStatus") String approvalStatus);
	
	@Modifying
	@Transactional
	@Query("update MilkTransaction t set t.approvalStatus = :approvalStatus where t.transactionId = :transactionId")
	void updateApprovalStatusByTransactionId(@Param(value = "transactionId") long transactionId, @Param(value = "approvalStatus") String approvalStatus);
}
