package com.tneaanalytics.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tneaanalytics.backend.dto.request.LoginRequest;
import com.tneaanalytics.backend.dto.request.RegisterRequest;
import com.tneaanalytics.backend.exception.UserNotFoundException;
import com.tneaanalytics.backend.model.User;
import com.tneaanalytics.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean checkIfUserAlreadyExistsByUsername(String username) {

        if (!repository.findByUsername(username).isEmpty()) {
            return true;
        }
        return false;
    }

    public Boolean checkIfUserAlreadyExistsByEmail(String email) {

        if (!repository.findByEmail(email).isEmpty()) {
            return true;
        }

        return false;
    }

    public LoginRequest registerUser(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        user = repository.save(user);
        return LoginRequest.builder().identifier(user.getEmail()).password(user.getPassword()).build();
    }

    public Boolean loginUserByEmail(LoginRequest request) {
        Optional<User> user = repository.findByEmail(request.getIdentifier());
        if (user.isEmpty())
            throw new UserNotFoundException("Email does not exist");
        else {
            if (passwordEncoder.matches(request.getPassword(), user.get().getPassword()))
                return true;
            else
                return false;
        }
    }

    public Boolean loginUserByUsername(LoginRequest request) {
        Optional<User> user = repository.findByUsername(request.getIdentifier());
        if (user.isEmpty())
            throw new UserNotFoundException("Username does not exist");
        else {
            if (passwordEncoder.matches(request.getPassword(), user.get().getPassword()))
                return true;
            else
                return false;
        }
    }
}
