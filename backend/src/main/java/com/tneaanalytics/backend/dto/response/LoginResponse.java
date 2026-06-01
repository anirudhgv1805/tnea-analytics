package com.tneaanalytics.backend.dto.response;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class LoginResponse {

    private String jwtToken;
}
