package com.milkeasy.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.milkeasy.model.MilkRate;


@Repository
public interface MilkRateRepository extends JpaRepository<MilkRate, Long>{

	@Query("SELECT t.rate FROM MilkRate t " +
	           "WHERE t.date = :date " +
	           "ORDER BY t.id desc")
	Float getRateByDate(@Param("date") Date date);
	
	Optional<MilkRate> findByDate(Date date);
}
