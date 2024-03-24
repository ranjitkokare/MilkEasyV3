package com.milkeasy.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.milkeasy.model.MilkRate;
import com.milkeasy.model.MilkTransaction;
import com.milkeasy.service.MilkRateService;

@Controller
public class MilkRateController {
	
	
	@Autowired
	private MilkRateService milkrateService;
	//view Set Rate page
	
		@GetMapping("/view_set_rate")
		public String viewSetRate(Model model) {
			MilkRate milkRate = new MilkRate();
			model.addAttribute("milkRate",milkRate);
			
			
			return "set_rate";
		}
		
		//set rate
		@PostMapping("/set_milkRate")
		public String setMilkRate(@ModelAttribute("milkRate") MilkRate milkRate) {
			milkrateService.setMilkRate(milkRate);
			return "redirect:/view_set_rate";
		}
}
