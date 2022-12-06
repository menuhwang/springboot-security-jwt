package com.likelion.springbootsecurityjwt.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final String secret = "secret";
    private final long tokenExpiration = 3600 * 1000;
    private final String USERNAME_KEY = "username";

    public String generateToken(String username) {
        Claims claims = Jwts.claims();
        claims.put(USERNAME_KEY, username);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean verify(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parse(token);
            return true;
        } catch (io.jsonwebtoken.SignatureException | MalformedJwtException exception) {
            // 잘못된 jwt signature
        } catch (io.jsonwebtoken.ExpiredJwtException exception) {
            // jwt 만료
        } catch (io.jsonwebtoken.UnsupportedJwtException exception) {
            // 지원하지 않는 jwt
        } catch (IllegalArgumentException exception) {
            // 잘못된 jwt 토큰
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.get(USERNAME_KEY).toString();
    }
}
