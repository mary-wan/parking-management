package com.kcbgroup.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.models.Slots;

@Service
public interface SlotService {
	
	List<Slots> getAllSlots();
	
	ResponseEntity<?> reserveSlot(Long staffId, Long levelId, Long slotId );
	
	ResponseEntity<?> bookSlot(String staffNumber, String levelNumber, String slotNumber );
	
	List<Slots> getLevelSlots(String levelNumber);
	

}
