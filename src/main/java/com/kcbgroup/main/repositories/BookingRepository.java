package com.kcbgroup.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcbgroup.main.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	Booking findByStaffNumber(String staffNumber);
	
	@Query(value = "SELECT * FROM booking WHERE booking.staff_number=:staffNumber AND booking.booking_status=:bookingStatus", nativeQuery = true)
	Booking findBooking(@Param("staffNumber") String staffNumber, @Param("bookingStatus") String bookingStatus);
	
}
