package com.milkeasy.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milkeasy.model.User;
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
		
		User user = new User();
		model.addAttribute("user", user);
		
		
		return "admin_profile";
	}
	
	@GetMapping(value="/collector_profile")
	public String collectorProfile(Model model, Principal principal) {
		String email = principal.getName();
		
		model.addAttribute("userProfile", userRepository.findByEmail(email));
		
		User user = new User();
		model.addAttribute("user", user);
		
		
		return "collector_profile";
	}
	
	@GetMapping(value="/farmer_profile")
	public String farmerProfile(Model model, Principal principal) {
		String email = principal.getName();
		
		model.addAttribute("userProfile", userRepository.findByEmail(email));
		
		User user = new User();
		model.addAttribute("user", user);
		
		
		return "farmer_profile";
	}
	
	@PostMapping(value = "/updateProfile")
	public String updateProfileById(User user, Principal principal, Model model) {
		
		String email = principal.getName();
		User loggedUser = userRepository.findByEmail(email);
		
		
		userRepository.updateProfileById(user.getFullName(), user.getMobile(), user.getUpiId(), 
				user.getAddress(), email);
		User updatedloggedUser = userRepository.findById(loggedUser.getId()).get();
		
		model.addAttribute("userProfile", updatedloggedUser);
		if (loggedUser.getMeRole().equals("admin")) {
			return "admin_profile";
			
		}else if(loggedUser.getMeRole().equals("collector")) {
			return "collector_profile";
			
		}else if(loggedUser.getMeRole().equals("farmer")) {
			return "farmer_profile";
			
		}
		return "me_home";
	}
}
