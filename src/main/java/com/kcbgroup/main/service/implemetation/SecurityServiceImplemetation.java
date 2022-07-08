package com.kcbgroup.main.service.implemetation;

import com.kcbgroup.main.dto.LoginDto;
import com.kcbgroup.main.repositories.SecurityRepository;
import com.kcbgroup.main.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityServiceImplemetation implements SecurityService {

    @Autowired
    SecurityRepository securityRepository;

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = securityRepository.findByStaffNumber(loginDto.getUsername()).getPassword();
        if(securityRepository.findByStaffNumber(loginDto.getUsername()) != null) {
            if(bCryptPasswordEncoder.matches(loginDto.getPassword(), password)){
                log.info("Password match");
                return new ResponseEntity<>(securityRepository.findByStaffNumberAndPassword(loginDto.getUsername(),password), HttpStatus.OK);
            }
        }
        log.info("No match");
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
