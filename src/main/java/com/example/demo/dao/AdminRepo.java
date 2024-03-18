package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
	List<Admin> findByuserNameAndPassword(String userName, String password);
	List<Admin> findByadminIdAndUserName(int adminId, String userName);


	Optional<Admin> findByUserName(String userName);
}
