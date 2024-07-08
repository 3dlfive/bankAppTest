package com.denystry.bankapp.controllers;

import com.denystry.bankapp.dto.auth.JwtRequest;
import com.denystry.bankapp.dto.auth.RegistrationUserDto;
import com.denystry.bankapp.service.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        log.info("Creating authentication token for user: {}", authRequest.getUsername());
        ResponseEntity<?> response = authService.createAuthToken(authRequest);
        log.info("Authentication token created for user: {}", authRequest.getUsername());
        return response;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        log.info("Registering new user: {}", registrationUserDto.getUsername());
        ResponseEntity<?> response = authService.createNewUser(registrationUserDto);
        log.info("User registered: {}", registrationUserDto.getUsername());
        return response;
    }
}
