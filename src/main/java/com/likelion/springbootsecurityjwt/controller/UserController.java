package com.likelion.springbootsecurityjwt.controller;

import com.likelion.springbootsecurityjwt.dto.LoginRequest;
import com.likelion.springbootsecurityjwt.dto.LoginResponse;
import com.likelion.springbootsecurityjwt.util.JwtProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final JwtProvider jwtProvider;

    public UserController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return new LoginResponse(jwtProvider.generateToken(loginRequest.getUsername()));
    }
}
