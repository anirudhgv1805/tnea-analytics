package com.tneaanalytics.backend.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    @Length(min = 8)
    private String password;
}
