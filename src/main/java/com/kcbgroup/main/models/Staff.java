package com.kcbgroup.main.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import com.kcbgroup.main.enums.ContractType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staff")
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;
	private String email;
	private String staffNumber;
	private String mobile;
	private String jobGroup;
	
	@Enumerated(EnumType.STRING)
	private ContractType contractType;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = {CascadeType.ALL})
	@JoinColumn(name = "role_id", nullable = false)	
	private Role role;

}
