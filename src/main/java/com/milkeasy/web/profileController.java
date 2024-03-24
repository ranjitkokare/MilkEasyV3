package com.milkeasy.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milkeasy.repository.UserRepository;

@Controller
@RequestMapping()
public class profileController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/admin_profile")
	public String adminProfile(Model model, Principal principal) {
		String email = principal.getName();
		
		model.addAttribute("userProfile", userRepository.findByEmail(email));
		
		return "admin_profile";
	}
	
	@GetMapping(value="/collector_profile")
	public String collectorProfile(Model model, Principal principal) {
		String email = principal.getName();
		
		model.addAttribute("userProfile", userRepository.findByEmail(email));
		
		return "collector_profile";
	}
	
	@GetMapping(value="/farmer_profile")
	public String farmerProfile(Model model, Principal principal) {
		String email = principal.getName();
		
		model.addAttribute("userProfile", userRepository.findByEmail(email));
		
		return "farmer_profile";
	}
}
