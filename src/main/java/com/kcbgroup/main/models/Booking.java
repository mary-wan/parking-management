package com.kcbgroup.main.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kcbgroup.main.enums.BookingStatus;
import com.kcbgroup.main.model.Customer;

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
	private Long levelId;
	private Long staffId;
	private Long slotId;
	
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
