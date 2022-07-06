package com.kcbgroup.main.service.implemetation;

import java.util.List;

import com.kcbgroup.main.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kcbgroup.main.models.Staff;
import com.kcbgroup.main.repositories.StaffRepository;
import com.kcbgroup.main.service.StaffService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Component
@Slf4j
public class StaffImplementationService implements StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public List<Staff> getAllStaff() {
		return staffRepository.findAll();
	}

	@Override
	public ResponseEntity<?> addStaff(Staff staff) {
		try {
			return new ResponseEntity<>(staffRepository.save(staff), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public ResponseEntity<?> deleteStaff(String staffNumber) {
		Staff staff = staffRepository.findByStaffNumber(staffNumber);
		try {
			if (staff != null) {
				staffRepository.delete(staff);
				return new ResponseEntity<>("Staff deleted", HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public ResponseEntity<?> login(LoginDto loginDto) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String password = staffRepository.findByStaffNumber(loginDto.getUsername()).getPassword();
		if(staffRepository.findByStaffNumber(loginDto.getUsername()) != null) {
			if(bCryptPasswordEncoder.matches(loginDto.getPassword(), password)){
				log.info("Password match");
				return new ResponseEntity<>(staffRepository.findByStaffNumberAndPassword(loginDto.getUsername(),password), HttpStatus.OK);
			}
		}
		log.info("No match");
		return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
