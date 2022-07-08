package com.kcbgroup.main.controllers;

import com.kcbgroup.main.dto.LoginDto;
import com.kcbgroup.main.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/login/security", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return securityService.login(loginDto);
    }
}
