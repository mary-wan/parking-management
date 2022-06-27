package com.kcbgroup.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcbgroup.main.models.Slots;
import com.kcbgroup.main.service.SlotService;

@RestController
@RequestMapping("/api")
public class SlotController {
	
	@Autowired
	private SlotService slotService;
	
	@RequestMapping(value ="/book/slot", method = RequestMethod.GET)
	public ResponseEntity<?> bookSlot(@RequestParam String staffNumber, @RequestParam String levelNumber, @RequestParam String slotNumber){
		return slotService.bookSlot(staffNumber, levelNumber, slotNumber);		
	}
	
	@RequestMapping(value ="/reserve/slot", method = RequestMethod.GET)
	public ResponseEntity<?> reserveSlot(@RequestParam String staffNumber, @RequestParam String levelNumber, @RequestParam String slotNumber){
		return slotService.reserveSlot(staffNumber, levelNumber, slotNumber);		
	}
	
	@RequestMapping(value ="/get/slots",method= RequestMethod.GET)
	public List<Slots> getAllSlots(){
		return slotService.getAllSlots();		
	}
	
	@RequestMapping(value ="/get/level/slots",method= RequestMethod.GET)
	public List<Slots> getLevelSlots(@RequestParam String LevelNumber){
		return slotService.getLevelSlots(LevelNumber);		
	}

}
