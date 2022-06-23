package com.kcbgroup.main.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kcbgroup.main.enums.SlotAvailability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "slots")
public class Slots {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String slotNumber;
	
	@Enumerated(EnumType.STRING)
	private SlotAvailability status;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = {CascadeType.ALL})
	@JoinColumn(name = "level_id", nullable = false)	
	private Levels level;


}
