package com.milkeasy.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkeasy.model.MilkRate;
import com.milkeasy.repository.MilkRateRepository;

@Service
public class MilkRateImpl implements MilkRateService{
	
	@Autowired
	private MilkRateRepository milkrateRepository;

	@Override
	public void setMilkRate(MilkRate milkRate) {
		this.milkrateRepository.save(milkRate);
	}

	
	@Override
	public boolean rateExistsForDate(Date date) {
		return milkrateRepository.findByDate(date).isPresent();
	}
}
