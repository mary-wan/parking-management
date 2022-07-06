package com.kcbgroup.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcbgroup.main.models.Slots;

public interface SlotRepository extends JpaRepository<Slots, Long> {
	
	@Query(value = "SELECT * FROM slots WHERE slots.slot_number=:slotNumber AND slots.level_id=:levelId", nativeQuery = true)
	Slots findBySlotNumber(@Param("slotNumber") String slotNumber, @Param("levelId") Long levelId);

	@Query(value = "SELECT * FROM slots WHERE slots.level_id=:levelId AND slots.status='AVAILABLE'", nativeQuery = true)
	List<Slots> findAvailableSlotsOnLevel(@Param("levelId") Long levelId);
	List<Slots> findByLevelId(Long levelId);


}
