package com.milkeasy.web.dto;

public class UserRegistrationDto {
	private String meRole;
	private String fullName;
	private String email;
	private String mobile;
	private String address;
	private String password;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String meRole, String fullName, String email, String mobile, String address, String password) {
		super();
		this.meRole = meRole;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.password = password;
	}
	
	public String getMeRole() {
		return meRole;
	}
	public void setMeRole(String meRole) {
		this.meRole = meRole;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
