package com.kcbgroup.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcbgroup.main.models.Staff;
import com.kcbgroup.main.service.StaffService;

@RestController
@RequestMapping("/api")
public class StaffController {
	
	@Autowired 
	private StaffService staffService;
	
	@RequestMapping(value = "all/staff", method = RequestMethod.GET)
	public List<Staff> getAllStaff(){
		return staffService.getAllStaff();
	}
	
	@RequestMapping(value = "add/staff", method = RequestMethod.POST)
	public ResponseEntity<?> addStaff(@RequestBody Staff staff){
		return staffService.addStaff(staff);
	}
	
	@RequestMapping(value = "delete/staff", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStaff(@RequestParam String staffNumber){
		return staffService.deleteStaff(staffNumber);		
	}

}
