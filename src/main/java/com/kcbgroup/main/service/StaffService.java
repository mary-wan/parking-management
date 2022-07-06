package com.kcbgroup.main.service;

import java.util.List;

import com.kcbgroup.main.dto.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.models.Staff;

@Service
public interface StaffService {
	
	List<Staff> getAllStaff();
	
	ResponseEntity<?> addStaff(Staff staff);
	
	ResponseEntity<?> deleteStaff(String staffNumber);

	ResponseEntity<?> login(LoginDto loginDto);
	

}
