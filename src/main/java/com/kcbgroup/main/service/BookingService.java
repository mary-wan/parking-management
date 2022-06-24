package com.kcbgroup.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.models.Booking;

@Service
public interface BookingService {
	
	List<Booking> getAllBooking();
	
	Booking showsStaffBooking(String staffNumber);
	
	ResponseEntity<?> checkIn(String staffNumber);
	
	ResponseEntity<?> checkOut(String staffNumber);

}
