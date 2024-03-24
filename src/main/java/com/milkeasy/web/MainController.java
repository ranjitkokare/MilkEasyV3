package com.milkeasy.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	public List<String> getMeRoleOptions(){
		List <String> meRoleOptions = new ArrayList < > ();
		meRoleOptions.add("admin");
		meRoleOptions.add("collector");
		meRoleOptions.add("farmer");
		return meRoleOptions;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/view_admin_login")
	public String viewAdminLoginPage(Model model) {
		
		model.addAttribute("meRole", "admin");
		List <String> meRoleOptions = getMeRoleOptions();
		model.addAttribute("meRoleOptions", meRoleOptions);		
		return "login";
	}
	
	@GetMapping("/view_collector_login")
	public String viewCollectorLoginPage(Model model) {
		
		model.addAttribute("meRole", "collector");
		List <String> meRoleOptions = getMeRoleOptions();
		model.addAttribute("meRoleOptions", meRoleOptions);
		return "login";
	}
	
	@GetMapping("/view_farmer_login")
	public String viewFarmerLoginPage(Model model) {
		
		model.addAttribute("meRole", "farmer");
		List <String> meRoleOptions = getMeRoleOptions();
		model.addAttribute("meRoleOptions", meRoleOptions);		
		return "login";
	}
	
	
	
	@GetMapping("/me_home")
	public String viewHomePage() {
		return "me_home";
	}
	@GetMapping("/login_home")
	public String viewLoginPage() {
		return "login_home";
	}
	@GetMapping("/feedback")
	public String viewFeedback() {
		return "feedback";
	}
	
}
