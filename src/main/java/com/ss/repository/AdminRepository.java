package com.ss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ss.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,String>{
	Admin findByUsername(String username);
	
	@Query(value="SELECT username FROM Admin",nativeQuery=true)
	public List<String> findUsenames();
}
