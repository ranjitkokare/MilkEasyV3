package com.milkeasy.web;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.milkeasy.model.MilkRate;
import com.milkeasy.model.MilkTransaction;
import com.milkeasy.service.MilkRateService;

@Controller
public class MilkRateController {
	
	
	@Autowired
	private MilkRateService milkrateService;
	//view Set Rate page
	
		@GetMapping("/view_set_rate")
		public String viewSetRate(Model model,@RequestParam(value = "success", required = false) String successMessage,@RequestParam(value = "error", required = false) String error) {
			MilkRate milkRate = new MilkRate();
			model.addAttribute("milkRate",milkRate);
			model.addAttribute("success", successMessage);
			model.addAttribute("error", error);
			return "set_rate";
		}
		
		//set rate
		@PostMapping("/set_milkRate")
		public String setMilkRate(@ModelAttribute("milkRate") MilkRate milkRate) {
			Date date = (Date) milkRate.getDate();
	        if (milkrateService.rateExistsForDate(date)) {
	            return "redirect:/view_set_rate?error=true";
	        }
			milkrateService.setMilkRate(milkRate);
			//model.addAttribute("successMessage", "Rate added successfully");
			return "redirect:/view_set_rate?success=true";
		}
}
