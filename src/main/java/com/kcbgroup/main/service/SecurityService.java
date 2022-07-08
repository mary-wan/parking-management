package com.kcbgroup.main.service;

import com.kcbgroup.main.dto.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {

    ResponseEntity<?> login(LoginDto loginDto);
}
