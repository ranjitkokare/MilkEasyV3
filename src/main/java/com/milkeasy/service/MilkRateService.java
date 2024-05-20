package com.milkeasy.service;

import java.sql.Date;
import java.time.LocalDate;

import com.milkeasy.model.MilkRate;

public interface MilkRateService {
	
	void setMilkRate(MilkRate milkRate);
	
	boolean rateExistsForDate(Date date);
}
