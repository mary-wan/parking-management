package com.kcbgroup.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kcbgroup.main.models.Booking;
import com.kcbgroup.main.service.BookingService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value = "/get/bookings", method = RequestMethod.GET)
	public List<Booking> getAllBookings(){
		return bookingService.getAllBooking();
	}
	
	@RequestMapping(value = "/get/staff/booking", method = RequestMethod.GET)
	public ResponseEntity<?> getStaffBooking(@RequestParam String staffNumber){
		return bookingService.showsStaffBooking(staffNumber);
	}
	
	@RequestMapping(value = "/checkIn/staff", method = RequestMethod.GET)
	public ResponseEntity<?> checkInStaff(@RequestParam String staffNumber){
		return bookingService.checkIn(staffNumber);
	}
	
	@RequestMapping(value = "/checkOut/staff", method = RequestMethod.GET)
	public ResponseEntity<?> checkOutStaff(@RequestParam String staffNumber){
		return bookingService.checkOut(staffNumber);
	}

}
