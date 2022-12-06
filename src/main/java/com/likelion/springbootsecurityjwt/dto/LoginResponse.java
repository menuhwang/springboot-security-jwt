package com.likelion.springbootsecurityjwt.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String token;

    private LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }
}
