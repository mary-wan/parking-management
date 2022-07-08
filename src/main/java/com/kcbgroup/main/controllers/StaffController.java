package com.kcbgroup.main.controllers;

import java.util.List;

import com.kcbgroup.main.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kcbgroup.main.models.Staff;
import com.kcbgroup.main.service.StaffService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StaffController {
	
	@Autowired 
	private StaffService staffService;
	
	@RequestMapping(value = "/all/staff", method = RequestMethod.GET)
	public List<Staff> getAllStaff(){
		return staffService.getAllStaff();
	}
	
	@RequestMapping(value = "/add/staff", method = RequestMethod.POST)
	public ResponseEntity<?> addStaff(@RequestBody Staff staff){
		return staffService.addStaff(staff);
	}
	
	@RequestMapping(value = "/delete/staff", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStaff(@RequestParam String staffNumber){
		return staffService.deleteStaff(staffNumber);		
	}

	@RequestMapping(value = "/login/staff", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
		return staffService.login(loginDto);
	}

}
