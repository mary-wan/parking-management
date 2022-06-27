package com.kcbgroup.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcbgroup.main.models.Booking;

@Service
public interface BookingService {
	
	List<Booking> getAllBooking();
	
	ResponseEntity<?> showsStaffBooking(String staffNumber);
	
	ResponseEntity<?> checkIn(String staffNumber);
	
	ResponseEntity<?> checkOut(String staffNumber);
	
	void checkUnreportedBooking();

}
