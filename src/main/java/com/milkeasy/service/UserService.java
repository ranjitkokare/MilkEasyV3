package com.milkeasy.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.milkeasy.model.User;
import com.milkeasy.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	
}
