package com.likelion.springbootsecurityjwt.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;

    private LoginRequest() {
    }

    public LoginRequest(String username) {
        this.username = username;
    }
}
