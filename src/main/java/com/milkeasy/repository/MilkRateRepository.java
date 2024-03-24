package com.milkeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkeasy.model.MilkRate;


@Repository
public interface MilkRateRepository extends JpaRepository<MilkRate, Long>{

	
}
