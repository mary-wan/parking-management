package com.kcbgroup.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcbgroup.main.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	
	Staff findByStaffNumber(String staffNumber);

}
