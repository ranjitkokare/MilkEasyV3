package com.milkeasy.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.milkeasy.model.MilkTransaction;
import com.milkeasy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	User findById(String id);
	User findByFullName(String fullName);
	List<User> findByMeRole(String meRole);
	
	@Modifying
	@Transactional
	@Query("UPDATE User t SET t.fullName = :fullName, t.mobile = :mobile, t.address = :address,"
			+ "t.upiId = :upiId"
			+ " where t.email = :email")
	void updateProfileById(@Param(value = "fullName") String fullName, @Param(value = "mobile") String mobile, 
			@Param(value = "upiId") String upiId, @Param(value = "address") String address, @Param(value = "email") String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE User t SET t.password = :password where t.email = :email")
	void updatePasswordByEmail(@Param(value = "password") String password, @Param(value = "email") String email);
}
