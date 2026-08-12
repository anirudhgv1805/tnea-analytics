package com.tneaanalytics.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tneaanalytics.backend.dto.request.LoginRequest;
import com.tneaanalytics.backend.dto.request.RegisterRequest;
import com.tneaanalytics.backend.dto.response.LoginResponse;
import com.tneaanalytics.backend.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register new user, login the yser and return LoginResponse
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> registerNewUser(@Valid @RequestBody RegisterRequest request) {
        var response = authService.registerNewUser(request);
        return new ResponseEntity<LoginResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginNewUser(@Valid @RequestBody LoginRequest request) {
        return new ResponseEntity<LoginResponse>(authService.login(request),HttpStatus.ACCEPTED);
    }

    // Password reset for user
    // Verify if there is a account
    //

}
