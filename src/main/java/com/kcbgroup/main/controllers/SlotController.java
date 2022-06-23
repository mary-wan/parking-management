package com.kcbgroup.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.kcbgroup.main.service.SlotService;

@RestController
@RequestMapping("/api")
public class SlotController {
	
	@Autowired
	SlotService slotService;
	
	@RequestMapping(value ="/book/slot/{staffNumber}/{levelNumber}/{slotNumber}")
	public ResponseEntity<?> bookSlot(@PathVariable String staffNumber, @PathVariable String levelNumber, @PathVariable String slotNumber){
		return slotService.bookSlot(staffNumber, levelNumber, slotNumber);
		
	}
	

}
