package com.ltweb.webjob.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {

    private String role;
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponse(String role, String accessToken) {
        this.role = role;
        this.accessToken = accessToken;
    }
}
