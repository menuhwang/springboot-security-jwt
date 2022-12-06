package com.likelion.springbootsecurityjwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final String secret = "secret";
    private final long tokenExpiration = 3600L;
    private final String USERNAME_KEY = "username";

    public String generateToken(String username) {
        Claims claims = Jwts.claims();
        claims.put(USERNAME_KEY, username);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
