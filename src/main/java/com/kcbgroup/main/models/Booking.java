package com.kcbgroup.main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kcbgroup.main.enums.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String staffNumber;  
	private String levelNumber;
	private Long staffId;
	private Long levelId;
	private String slotNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private BookingStatus bookingStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date bookingTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true)
	private Date checkInTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true)
	private Date checkOutTime;
	
}
