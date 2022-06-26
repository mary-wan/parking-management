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
import com.kcbgroup.main.models.Levels;
import com.kcbgroup.main.models.Slots;
import com.kcbgroup.main.models.Staff;
import com.kcbgroup.main.repositories.BookingRepository;
import com.kcbgroup.main.repositories.LevelRepository;
import com.kcbgroup.main.repositories.SlotRepository;
import com.kcbgroup.main.repositories.StaffRepository;
import com.kcbgroup.main.service.SlotService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SlotServiceImplemetation implements SlotService {

	@Autowired
	private SlotRepository slotRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private LevelRepository levelRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Slots> getAllSlots() {

		return slotRepository.findAll();
	}

	@Override
	public ResponseEntity<?> reserveSlot(String staffNumber, String levelNumber, String slotNumber) {

		Staff staff = staffRepository.findByStaffNumber(staffNumber);
		Levels level = levelRepository.findByLevelNumber(levelNumber);
		if (slotRepository.findBySlotNumber(slotNumber, level.getId()) != null) {
			Slots slot = slotRepository.findBySlotNumber(slotNumber, level.getId());

			log.info("****** STEP 0 ***{}", slot.getStatus());
			Booking booking_ = bookingRepository.findInprogressOrReservedBooking(staffNumber.toString());
			if (booking_ == null) {
				log.info("****** STEP 1 ************");
				if (slot.getStatus() == SlotAvailability.AVAILABLE) {
					log.info("****** STEP 2 ************");
					if (level.getLevelNumber().equalsIgnoreCase("1") && (staff.getJobGroup().equalsIgnoreCase("E"))) {
						log.info("****** STEP 3 ************");
						Booking booking = new Booking();
						booking.setLevelNumber(level.getLevelNumber());
						booking.setSlotNumber(slot.getSlotNumber());
						booking.setLevelId(level.getId());
						booking.setStaffId(staff.getId());
						booking.setStaffNumber(staffNumber);
						booking.setBookingTime(new Date());
						booking.setBookingStatus(BookingStatus.RESERVED);
						bookingRepository.save(booking);
						slot.setStatus(SlotAvailability.RESERVED);
						slotRepository.save(slot);
						return new ResponseEntity<>("Reservation successfull.", HttpStatus.CREATED);
					}
				}
			}else{
				return new ResponseEntity<>("Staff already has a booked slot.", HttpStatus.ALREADY_REPORTED);
			}
		}

		return null;
	}

	@Override
	public ResponseEntity<?> bookSlot(String staffNumber, String levelNumber, String slotNumber) {

		Staff staff = staffRepository.findByStaffNumber(staffNumber);
		Levels level = levelRepository.findByLevelNumber(levelNumber);

		if (level.getLevelNumber() == null) {
			log.info("------------ Lvl No ISNULL");
		} else {
			log.info("------------ Lvl No ISNOTNULL");
			log.info("------------ {}", level.getLevelNumber());
		}

		if (slotRepository.findBySlotNumber(slotNumber, level.getId()) != null) {
			Slots slot = slotRepository.findBySlotNumber(slotNumber, level.getId());

			log.info("****** STEP 0 ***{}", slot.getStatus());
			Booking booking_ = bookingRepository.findInprogressOrReservedBooking(staffNumber.toString());
			if (booking_ == null) {
				log.info("****** STEP 1 ************");
				if (slot.getStatus() == SlotAvailability.AVAILABLE) {
					log.info("****** STEP 2 ************");
					if (level.getLevelNumber().equalsIgnoreCase("1")
							&& (staff.getJobGroup().equalsIgnoreCase("C") || staff.getJobGroup().equalsIgnoreCase("D")
									|| staff.getJobGroup().equalsIgnoreCase("E"))) {
						log.info("****** STEP 3 ************");
						Booking booking = new Booking();
						booking.setLevelNumber(level.getLevelNumber());
						booking.setSlotNumber(slot.getSlotNumber());
						booking.setLevelId(level.getId());
						booking.setStaffId(staff.getId());
						booking.setStaffNumber(staffNumber);
						booking.setBookingTime(new Date());
						booking.setBookingStatus(BookingStatus.INPROGRESS);
						bookingRepository.save(booking);
						slot.setStatus(SlotAvailability.BOOKED);
						slotRepository.save(slot);
						return new ResponseEntity<>("Booking successfull.", HttpStatus.CREATED);

					} else if (level.getLevelNumber().equalsIgnoreCase("2")
							&& (staff.getJobGroup().equalsIgnoreCase("C")
									|| staff.getJobGroup().equalsIgnoreCase("B"))) {
						log.info("****** STEP 4 ******");
						Booking booking = new Booking();
						booking.setLevelNumber(level.getLevelNumber());
						booking.setSlotNumber(slot.getSlotNumber());
						booking.setLevelId(level.getId());
						booking.setStaffId(staff.getId());
						booking.setStaffNumber(staffNumber);
						booking.setBookingTime(new Date());
						booking.setBookingStatus(BookingStatus.INPROGRESS);
						bookingRepository.save(booking);
						slot.setStatus(SlotAvailability.BOOKED);
						slotRepository.save(slot);
						return new ResponseEntity<>("Booking successfull.", HttpStatus.CREATED);

					} else if (level.getLevelNumber().equalsIgnoreCase("3")
							&& (staff.getJobGroup().equalsIgnoreCase("A")
									|| staff.getJobGroup().equalsIgnoreCase("B"))) {
						log.info("****** STEP 5 ******");
						Booking booking = new Booking();
						booking.setLevelNumber(level.getLevelNumber());
						booking.setSlotNumber(slot.getSlotNumber());
						booking.setLevelId(level.getId());
						booking.setStaffId(staff.getId());
						booking.setStaffNumber(staffNumber);
						booking.setBookingTime(new Date());
						booking.setBookingStatus(BookingStatus.INPROGRESS);
						bookingRepository.save(booking);
						slot.setStatus(SlotAvailability.BOOKED);
						slotRepository.save(slot);
						return new ResponseEntity<>("Booking successfull.", HttpStatus.CREATED);
					} else {
						log.info("****** STEP 6 EXIT ******");
						log.info("{} Is not allowed to park at level {}.", staffNumber);
						return new ResponseEntity<>("Not allowed to park at this level.", HttpStatus.OK);
					}
				} else {
					log.info("****** STEP 8 EXIT ******");
					log.info("Slot {} is not available", slotNumber);
					return new ResponseEntity<>("Slot is not available.", HttpStatus.CONFLICT);
				}
			} else {
				log.info("****** STEP 7 EXIT ******");
				log.info("{} has a booked slot.", staffNumber);
				return new ResponseEntity<>("Staff already has a booked slot.", HttpStatus.ALREADY_REPORTED);
			}
		}
		return null;
	}

	@Override
	public List<Slots> getLevelSlots(String levelNumber) {
		return slotRepository.findByLevelId((levelRepository.findByLevelNumber(levelNumber)).getId());
	}
}
