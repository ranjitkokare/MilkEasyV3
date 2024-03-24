package com.milkeasy.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milkeasy.service.UserService;
import com.milkeasy.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	public List<String> getMeRoleOptions(){
		List <String> meRoleOptions = new ArrayList < > ();
		meRoleOptions.add("admin");
		meRoleOptions.add("collector");
		meRoleOptions.add("farmer");
		return meRoleOptions;
	}

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm(Model model) {
		List <String> meRoleOptions = getMeRoleOptions();
		model.addAttribute("meRoleOptions", meRoleOptions);	
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
