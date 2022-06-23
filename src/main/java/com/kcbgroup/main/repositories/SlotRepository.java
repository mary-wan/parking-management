package com.kcbgroup.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcbgroup.main.models.Slots;

public interface SlotRepository extends JpaRepository<Slots, Long> {
	
	Slots findBySlotNumber(String slotNumber);

}
