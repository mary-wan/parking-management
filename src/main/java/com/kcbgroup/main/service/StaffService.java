package com.kcbgroup.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.models.Staff;

@Service
public interface StaffService {
	
	List<Staff> getAllStaff();
	
	ResponseEntity<?> addStaff(Staff staff);
	
	ResponseEntity<?> deleteStaff(Long stuffId);
	

}
