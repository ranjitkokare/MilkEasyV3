package com.milkeasy.web;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milkeasy.model.MilkTransaction;
import com.milkeasy.model.User;
import com.milkeasy.repository.MilkTransactionRepo;
import com.milkeasy.repository.UserRepository;
import com.milkeasy.service.MilkTransactionService;

@Controller
@RequestMapping(value="/")
public class DashboardController {
	
	@Autowired
	private MilkTransactionService milktransactionService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MilkTransactionRepo milkTransactionRepo;
	
	@GetMapping
	public String home(Principal principal,Model model,Long transactionId) {
		
		String email = principal.getName();
		User loggedUser = userRepository.findByEmail(email);
		if (loggedUser.getMeRole().equals("admin")) {
			
			Date fromYesterday =  java.sql.Date.valueOf(LocalDate.now());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -9);
			Date toEndofWeek =  cal.getTime();
			Long adminId = loggedUser.getId();
			//passing logged user name to dashboard
			String adminName = loggedUser.getFullName();
			model.addAttribute("adminName",adminName);
//			MilkTransaction milkTransaction = new MilkTransaction();
//			String approvalStatus = milkTransaction.getApprovalStatus();
//			
			Double totalApprovedQuantity = milkTransactionRepo.getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndAdminIdAndApprovalStatus(toEndofWeek,
					fromYesterday, adminId, "approved");
			model.addAttribute("totalApprovedQuantity", totalApprovedQuantity);
			
			Double totalPendingQuantity = milkTransactionRepo.getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndAdminIdAndApprovalStatus(toEndofWeek,
					fromYesterday, adminId, "pending");
			model.addAttribute("totalPendingQuantity", totalPendingQuantity);
			
			return "admin_dashboard";
		}
		else if (loggedUser.getMeRole().equals("collector")) {
			Date fromYesterday =  java.sql.Date.valueOf(LocalDate.now());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -9);
			Date toEndofWeek =  cal.getTime();
			Long collectorId = loggedUser.getId();
			String approvalStatus = "approved";
			List<MilkTransaction> milk_list = milktransactionService.getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorIdAndApprovalStatus(toEndofWeek,fromYesterday, collectorId, approvalStatus);
			model.addAttribute("listMilkTransaction", milk_list);
			
			Double totalAmount = milkTransactionRepo.getTotalAmountByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorId(toEndofWeek,fromYesterday, collectorId);
			model.addAttribute("totalAmount", totalAmount);
			
			Double totalQuantity = milkTransactionRepo.getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorId(toEndofWeek,fromYesterday, collectorId);
			model.addAttribute("totalQuantity", totalQuantity);
			
			return "collector_dashboard";
		}
		else if (loggedUser.getMeRole().equals("farmer")) {
			Date fromYesterday =  java.sql.Date.valueOf(LocalDate.now());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -9);
			Date toEndofWeek =  cal.getTime();
			Long farmerId = loggedUser.getId();
			String approvalStatus = "approved";
			List<MilkTransaction> milk_list = milktransactionService.getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerIdAndApprovalStatus(toEndofWeek,fromYesterday, farmerId, approvalStatus);
			model.addAttribute("listMilkTransaction", milk_list);
			
			Double totalAmount = milkTransactionRepo.getTotalAmountByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(toEndofWeek,fromYesterday, farmerId);
			model.addAttribute("totalAmount", totalAmount);
			
			Double totalQuantity = milkTransactionRepo.getTotalQuantityByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerId(toEndofWeek,fromYesterday, farmerId);
			model.addAttribute("totalQuantity", totalQuantity);
			
			return "farmer_dashboard";
		}
		return "me_home";
	}
}
