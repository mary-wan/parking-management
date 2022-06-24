package com.kcbgroup.main.service.implemetation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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
	public ResponseEntity<?> showsStaffBooking(String staffNumber) {
		Booking booking = bookingRepository.findBooking(staffNumber.toString(), BookingStatus.INPROGRESS.toString());
		if(booking !=null) {
			return new ResponseEntity<>(booking,HttpStatus.OK);
		}
		return new ResponseEntity<>("Staff has no booked slot.",HttpStatus.NOT_FOUND);
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

	@Override
	@Scheduled(fixedRate = 10000)
	public void checkUnreportedBooking() {
		try {
			  List<Booking> unreportedBookings = bookingRepository.findByBookingStatus(BookingStatus.INPROGRESS);
			  for(Booking booking :unreportedBookings){
				  
				  //GET INPROGRESS BOOKINGS WITHIN 3 HOURS 
				  List<Booking> lst = bookingRepository.findBookingTimeExcedes3Hours(booking.getId(),BookingStatus.INPROGRESS.toString());
				  if(lst.size() > 0) {
					  //RELEASE THEIR BOOKING
					  log.info("------ EXPIRED BOOKINGS --- {}", booking.getStaffId());
					  booking.setBookingStatus(BookingStatus.EXPIRED);
					  bookingRepository.save(booking);
					  Slots slot = slotRepository.findBySlotNumber(booking.getSlotNumber(), booking.getLevelId());
					  slot.setStatus(SlotAvailability.AVAILABLE);
					  slotRepository.save(slot);
				  }
	          }
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
}
