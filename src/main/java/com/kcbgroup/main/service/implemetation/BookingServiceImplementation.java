package com.kcbgroup.main.service.implemetation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.enums.BookingStatus;
import com.kcbgroup.main.enums.SlotAvailability;
import com.kcbgroup.main.models.Booking;
import com.kcbgroup.main.models.Slots;
import com.kcbgroup.main.repositories.BookingRepository;
import com.kcbgroup.main.repositories.LevelRepository;
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
	
	@Autowired
	LevelRepository levelRepository;

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
		Booking booking = bookingRepository.findBooking(staffNumber.toString(), BookingStatus.INPROGRESS.toString());
		if (booking != null && booking.getBookingStatus() == BookingStatus.INPROGRESS){
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
		Booking booking = bookingRepository.findBooking(staffNumber.toString(), BookingStatus.INPROGRESS.toString());
		if (booking != null && booking.getBookingStatus() == BookingStatus.INPROGRESS) {
			log.info("****** STEP 1 ******");
			booking.setCheckOutTime(new Date());
			booking.setBookingStatus(BookingStatus.CHECKEDOUT);
			bookingRepository.save(booking);
     		Slots slot = slotRepository.findBySlotNumber(booking.getSlotNumber(), booking.getLevelId());
     		slot.setStatus(SlotAvailability.AVAILABLE);
     		slotRepository.save(slot);
			return new ResponseEntity<>("Staff checked out",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Booking not found",HttpStatus.NOT_FOUND);
		}
	}

}
