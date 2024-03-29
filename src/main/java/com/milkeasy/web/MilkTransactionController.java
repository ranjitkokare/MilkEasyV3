package com.milkeasy.web;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.milkeasy.model.CustomDateRange;
import com.milkeasy.model.MilkRate;
import com.milkeasy.model.MilkTransaction;
import com.milkeasy.model.User;
import com.milkeasy.repository.MilkRateRepository;
import com.milkeasy.repository.MilkTransactionRepo;
import com.milkeasy.repository.UserRepository;
import com.milkeasy.service.EmailSenderService;
import com.milkeasy.service.GeneratePDFService;
import com.milkeasy.service.MilkTransactionService;

@Controller
public class MilkTransactionController {
	
	@Autowired
	private EmailSenderService senderService;
	
	@Autowired
	private MilkTransactionService milktransactionService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GeneratePDFService generatePDFService;
	@Autowired
	private MilkRateRepository milkRateRepository;
	@Autowired
	private MilkTransactionRepo milkTransactionRepo;
	
	private List<String> getAllFarmerFullNames(){
		List <User> allFarmers = userRepository.findByMeRole("farmer");
		List <String> allFarmerFullNames = new ArrayList();
		for (User farmerUser : allFarmers) {
			allFarmerFullNames.add(farmerUser.getFullName());
		}
		return allFarmerFullNames;
	}
	private List<String> getAllAdminFullNames(){
		List <User> alladmins = userRepository.findByMeRole("admin");
		List <String> allAdminFullNames = new ArrayList();
		for (User adminUser : alladmins) {
			allAdminFullNames.add(adminUser.getFullName());
		}
		return allAdminFullNames;
	}
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
	public String getCollectorStatementDates(Model model, @ModelAttribute("customDateRange") CustomDateRange customDateRange, Principal principal) {
		Date fromDate =  customDateRange.getCustomFromdate();
		Date toDate =  customDateRange.getCustomTodate();
		String buttonClicked = customDateRange.getButtonClicked();
		
		String email = principal.getName();
		User loggedUser = userRepository.findByEmail(email);
		Long collectorId = loggedUser.getId();
		String approvalStatus = "approved";
		List<MilkTransaction> allMilkTransaction = milktransactionService.getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndCollectorIdAndApprovalStatus(fromDate,toDate,collectorId, approvalStatus);
		
		if (buttonClicked.equals(",download")) {
			generatePDFService.createMilkTransactionStatementPdf(loggedUser, allMilkTransaction, customDateRange);
		}
		
		model.addAttribute("listMilkTransaction", allMilkTransaction);
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
		String approvalStatus = "approved";
		List<MilkTransaction> allMilkTransaction =  milktransactionService.getMilkTransactionByCollectionDateGreaterThanEqualAndCollectionDateLessThanEqualAndFarmerIdAndApprovalStatus(fromDate,toDate,farmerId, approvalStatus);
		
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
		
		model.addAttribute("allFarmerFullNames", getAllFarmerFullNames());
		model.addAttribute("allAdminFullNames", getAllAdminFullNames());
		return "add_milk_collection";
	} 
	
	@PostMapping("/add_mc")
	public String addMilkTransaction(@ModelAttribute("milkTransaction") MilkTransaction milkTransaction, Principal principal){
		
		String email = principal.getName();
		User loggedUser = userRepository.findByEmail(email);
		milkTransaction.setCollectorId(loggedUser.getId());
		milkTransaction.setCollectorFullName(loggedUser.getFullName());
		
		User farmerUser = userRepository.findByFullName(milkTransaction.getFarmerFullName());
		milkTransaction.setFarmerId(farmerUser.getId());
		
		User adminUser = userRepository.findByFullName(milkTransaction.getAdminFullName());
		milkTransaction.setAdminId(adminUser.getId());
		
		Float milkRate = milkRateRepository.getRateByDate(milkTransaction.getCollectionDate());
		milkTransaction.setApprovalStatus("pending");
		milkTransaction.setRate(milkRate);
		milkTransaction.setAmount(milkRate * milkTransaction.getQuantity());
		
		//save milk transaction
		milktransactionService.addMilkTransaction(milkTransaction);
		
		
    	return "redirect:/view_add_mc";
		
	}
	
	@GetMapping("/show_pending_approvals")
	public String showPendingApprovals(Model model, Principal principal) {
		String email = principal.getName();
		User adminUser = userRepository.findByEmail(email);
		
		List<MilkTransaction> milk_list = milktransactionService.getMilkTransactionByAdminIdAndApprovalStatus(adminUser.getId(), "pending");
		model.addAttribute("listMilkTransaction", milk_list);
		return "admin_approvals";
		
	}
	
	@GetMapping("/approveMilkTransaction/{approval_status}/{id}")
	public String approveMilkTransaction(@PathVariable (value = "id") long id, @PathVariable (value = "approval_status") String approval_status,  Model model, Principal principal) {
		
		MilkTransaction milkTransaction = milktransactionService.getMilkTransactionBytransactionId(id);
		milkTransaction.setApprovalStatus(approval_status);
		milkTransactionRepo.updateApprovalStatusByTransactionId(milkTransaction.getTransactionId(), milkTransaction.getApprovalStatus());
		
		senderService.sendMilkTransactionToFarmerAndCollector(milkTransaction);
		
		String email = principal.getName();
		User adminUser = userRepository.findByEmail(email);
		
		List<MilkTransaction> milk_list = milktransactionService.getMilkTransactionByAdminIdAndApprovalStatus(adminUser.getId(), "pending");
		model.addAttribute("listMilkTransaction", milk_list);
		
		return "admin_approvals";
	}
	
	
}
