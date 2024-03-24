package com.milkeasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milkeasy.model.MilkTransaction;
import com.milkeasy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	User findById(String id);
	User findByFullName(String fullName);
	List<User> findByMeRole(String meRole);
}
