package com.tneaanalytics.backend.security.authorization;

import java.security.Key;
import java.util.Date;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    // Time in milliseconds
    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(String userId) {

        byte[] secretKeyBytes = Base64.getDecoder().decode(secretKey);
        Key key = Keys.hmacShaKeyFor(secretKeyBytes);

        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }

    public String extractUserName(String token) {
        return parseToken(token).getSubject();
    }

    public boolean validateToken(String token, String userName) {
        String extractedUserName = extractUserName(token);
        return (extractedUserName.equals(userName) && !isTokenExpired(token));
    }

    public Claims parseToken(String token) {

        byte[] secretKeyBytes = Base64.getDecoder().decode(secretKey);
        SecretKey key = Keys.hmacShaKeyFor(secretKeyBytes);

        JwtParser jwtParser = Jwts.parser()
                .verifyWith(key)
                .build();
        return jwtParser.parseSignedClaims(token)
                .getPayload();
    }
}
