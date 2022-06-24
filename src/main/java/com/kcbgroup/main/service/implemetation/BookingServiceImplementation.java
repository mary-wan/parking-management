package com.kcbgroup.main.service.implemetation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.enums.BookingStatus;
import com.kcbgroup.main.models.Booking;
import com.kcbgroup.main.models.Slots;
import com.kcbgroup.main.repositories.BookingRepository;
import com.kcbgroup.main.repositories.SlotRepository;
import com.kcbgroup.main.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BookingServiceImplementation implements BookingService{
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	SlotRepository slotRepository;

	@Override
	public List<Booking> getAllBooking() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking showsStaffBooking(String staffNumber) {
		return bookingRepository.findByStaffNumber(staffNumber);
	}

	@Override
	public ResponseEntity<?> checkIn(String staffNumber) {
		Booking booking = bookingRepository.findByStaffNumber(staffNumber);
		if (booking != null) {
			log.info("****** STEP 1 ******");
			booking.setCheckInTime(new Date());
			bookingRepository.save(booking);
			
			return new ResponseEntity<>("Staff checked in",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Booking not found",HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<?> checkOut(String staffNumber) {
		Booking booking = bookingRepository.findByStaffNumber(staffNumber);
		if (booking != null) {
			log.info("****** STEP 1 ******");
			booking.setCheckOutTime(new Date());
			booking.setBookingStatus(BookingStatus.CHECKEDOUT);
			Optional<Slots> slot = slotRepository.findById(booking.getSlotId());
			if(slot.isPresent()) {
				
			}
			bookingRepository.save(booking);
			
			return new ResponseEntity<>("Staff checked out",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Booking not found",HttpStatus.NOT_FOUND);
		}
	}

}
