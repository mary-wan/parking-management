package com.kcbgroup.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcbgroup.main.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	Booking findByStaffNumber(String staffNumber);
}
