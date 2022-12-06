package com.likelion.springbootsecurityjwt.dto;

import lombok.Getter;

@Getter
public class UserResponse {
    private String username;

    private UserResponse() {
    }

    public UserResponse(String username) {
        this.username = username;
    }
}
