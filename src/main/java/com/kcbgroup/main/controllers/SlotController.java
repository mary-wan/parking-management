package com.kcbgroup.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kcbgroup.main.models.Slots;
import com.kcbgroup.main.service.SlotService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
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

	@RequestMapping(value ="/get/available/slots",method= RequestMethod.GET)
	public List<Slots> getAvailableLevelSlots(@RequestParam Long levelId){
		return slotService.getAvailableLevelSlots(levelId);
	}

}
