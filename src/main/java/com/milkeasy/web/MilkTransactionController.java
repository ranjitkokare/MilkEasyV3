package com.milkeasy.web;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.milkeasy.model.CustomDateRange;
import com.milkeasy.model.MilkTransaction;
import com.milkeasy.model.User;
import com.milkeasy.repository.UserRepository;
import com.milkeasy.service.GeneratePDFService;
import com.milkeasy.service.MilkTransactionService;

@Controller
public class MilkTransactionController {
	
	@Autowired
	private MilkTransactionService milktransactionService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GeneratePDFService generatePDFService;
	
	@GetMapping("/admin_statement_range")
	public String showAdminStatementDates(Model model) {
		CustomDateRange customDateRange = new CustomDateRange();
		model.addAttribute("customDateRange",customDateRange);
		return "admin_statement_range";
	}
	@PostMapping("/adminStatementRange")
	public String getAdminStatementDates(Model model, @ModelAttribute("customDateRange") CustomDateRange customDateRange) {
		Date fromDate =  customDateRange.getCustomFromdate();
		Date toDate =  customDateRange.getCustomTodate();
		model.addAttribute("listMilkTransaction", milktransactionService.getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(fromDate,toDate));
		return "admin_statement";
	}
	
	@GetMapping("/collector_statement_range")
	public String showCollectorStatementDates(Model model) {
		CustomDateRange customDateRange = new CustomDateRange();
		model.addAttribute("customDateRange",customDateRange);
		return "collector_statement_range";
	}
	@PostMapping("/collectorStatementRange")
	public String getCollectorStatementDates(Model model, @ModelAttribute("customDateRange") CustomDateRange customDateRange) {
		Date fromDate =  customDateRange.getCustomFromdate();
		Date toDate =  customDateRange.getCustomTodate();
		model.addAttribute("listMilkTransaction", milktransactionService.getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqual(fromDate,toDate));
		return "collector_statement";
	}
	
	@GetMapping("/farmer_statement_range")
	public String showFarmerStatementDates(Model model) {
		CustomDateRange customDateRange = new CustomDateRange();
		model.addAttribute("customDateRange",customDateRange);
		return "farmer_statement_range";
	}
	@PostMapping("/farmerStatementRange")
	public String getFarmerStatementDates(Model model, @ModelAttribute("customDateRange") CustomDateRange customDateRange, Principal principal) {
		Date fromDate =  customDateRange.getCustomFromdate();
		Date toDate =  customDateRange.getCustomTodate();
		String buttonClicked = customDateRange.getButtonClicked();
		
		String email = principal.getName();
		User loggedUser = userRepository.findByEmail(email);
		Long farmerId = loggedUser.getId();
		List<MilkTransaction> allMilkTransaction =  milktransactionService.getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(fromDate,toDate, farmerId);
		
		if (buttonClicked.equals(",download")) {
			generatePDFService.createMilkTransactionStatementPdf(loggedUser, allMilkTransaction, customDateRange);
		}
				
		model.addAttribute("listMilkTransaction", allMilkTransaction);
		return "farmer_statement";
	}
	
	
	@GetMapping("/view_add_mc")
	public String viewAdd_mc(Model model) {
		//create model attribute to bind form data
		MilkTransaction milkTransaction = new MilkTransaction();
		model.addAttribute("milkTransaction",milkTransaction);
		return "add_milk_collection";
	} 
	
	@PostMapping("/add_mc")
	public String addMilkTransaction(@ModelAttribute("milkTransaction") MilkTransaction milkTransaction){
		//save miktransaction
		milktransactionService.addMilkTransaction(milkTransaction);
		return "redirect:/view_add_mc";
		
	}
	
	
	
	
}
