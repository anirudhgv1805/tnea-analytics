package com.tneaanalytics.backend.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tneaanalytics.backend.dto.request.LoginRequest;
import com.tneaanalytics.backend.dto.request.RegisterRequest;
import com.tneaanalytics.backend.dto.response.LoginResponse;
import com.tneaanalytics.backend.exception.DuplicateUserException;
import com.tneaanalytics.backend.security.authorization.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        if (EmailValidator.getInstance().isValid(request.getIdentifier())) {
            userService.loginUserByEmail(request);
        } else {
            userService.loginUserByUsername(request);
        }

        return LoginResponse.builder().jwtToken(jwtUtil.generateToken(request.getIdentifier())).build();

    }

    public LoginResponse registerNewUser(RegisterRequest request) throws DuplicateUserException {
        if (userService.checkIfUserAlreadyExistsByEmail(request.getEmail())) {
            throw new DuplicateUserException("This email already exists!");
        }

        // Registering the user throws if duplicates are there
        userService.registerUser(request);
        return login(
                LoginRequest.builder().identifier(request.getUsername()).password(request.getPassword()).build());
    }

}
