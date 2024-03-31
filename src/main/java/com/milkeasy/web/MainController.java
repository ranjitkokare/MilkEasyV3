package com.milkeasy.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.milkeasy.model.MilkTransaction;
import com.milkeasy.repository.UserRepository;
import com.milkeasy.service.EmailSenderService;
import com.milkeasy.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private EmailSenderService senderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
	
	@GetMapping("/about_us")
	public String view_about_us() {
		return "about_us";
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
	
	@GetMapping("/forgotPassword")
	public String viewForgetPassword(Model model) {
		
		String inputEmail = new String();
		model.addAttribute("inputEmail", inputEmail);
		
		return "forgot_password";
	}
	
	@PostMapping("/resetPasswordsendmail")
	public String sendResetPasswordLink(@RequestParam("inputEmail") String inputEmail) {
		
		senderService.sendResetPasswordEmail(inputEmail);
		
		return "/login_home";
	}
	
	@GetMapping("/resetPassword/{email}")
	public String showUpdatePasswordForm(@PathVariable (value = "email") String email,  Model model) {
		
		model.addAttribute("resetEmail", new String(Base64.getDecoder().decode(email)));
		com.milkeasy.model.User user = new com.milkeasy.model.User();
		model.addAttribute("user", user);
		
		return "reset_password";
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@ModelAttribute("user") com.milkeasy.model.User user) {
		
		String email = user.getEmail();
		user.setEmail(email.substring(0, email.length() - 1));
		userService.updatePassword(user);
		
		return "redirect:/me_home";
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
