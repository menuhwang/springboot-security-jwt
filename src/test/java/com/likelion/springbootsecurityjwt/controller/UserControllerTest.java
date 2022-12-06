package com.likelion.springbootsecurityjwt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.springbootsecurityjwt.config.SecurityConfig;
import com.likelion.springbootsecurityjwt.dto.LoginRequest;
import com.likelion.springbootsecurityjwt.dto.LoginResponse;
import com.likelion.springbootsecurityjwt.util.JwtAuthenticationFilter;
import com.likelion.springbootsecurityjwt.util.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ImportAutoConfiguration(SecurityConfig.class)
@Import(JwtProvider.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private LoginRequest loginRequest = new LoginRequest("tester");

    @Test
    void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andDo(print());
    }

    @Test
    void check() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").exists())
                    .andDo(print())
                .andReturn().getResponse().getContentAsString();

        LoginResponse loginResponse = objectMapper.readValue(response, LoginResponse.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/me")
                        .header(HttpHeaders.AUTHORIZATION, loginResponse.getToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists())
                .andDo(print());
    }
}