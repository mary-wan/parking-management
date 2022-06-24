package com.kcbgroup.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcbgroup.main.enums.BookingStatus;
import com.kcbgroup.main.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	@Query(value = "SELECT * FROM booking WHERE booking.staff_number=:staffNumber AND booking.booking_status=:bookingStatus", nativeQuery = true)
	Booking findBooking(@Param("staffNumber") String staffNumber, @Param("bookingStatus") String bookingStatus);
	
	@Query(value = "SELECT * FROM booking WHERE booking.booking_time < DATE_SUB(NOW(), INTERVAL 3 HOUR) AND booking.id=:id AND booking.booking_status=:bookingStatus", nativeQuery = true)
    List<Booking> findBookingTimeExcedes3Hours(@Param("id") Long id, @Param("bookingStatus") String bookingStatus);
	
//	@Query(value = "SELECT * FROM booking WHERE booking.booking_time < DATE_SUB(NOW(), INTERVAL 1 HOUR) AND booking.id=:id", nativeQuery = true)
//    List<Booking> findBookingTimeExcedes3Hours(@Param("id") Long id);
	
	List<Booking> findByBookingStatus(BookingStatus bookingStatus);
	
	Booking findByStaffNumber(String staffNumber);
	
}
