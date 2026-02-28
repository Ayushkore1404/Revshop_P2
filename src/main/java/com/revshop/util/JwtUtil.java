package com.revshop.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Use a secure key for production - this should be stored securely
    private static final String SECRET_KEY = "mySecretKeyForJWTTokenGenerationThatIsVeryLongAndSecureForProductionUse123456789";
    private static final long JWT_EXPIRATION_MS = 86400000; // 24 hours

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email, Long userId, String role, String name) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        claims.put("name", name);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("❌ Invalid JWT token: " + e.getMessage());
            return null;
        }
    }

    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        if (claims != null && claims.get("userId") != null) {
            return Long.valueOf(claims.get("userId").toString());
        }
        return null;
    }

    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.get("role", String.class) : null;
    }

    public String extractName(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.get("name", String.class) : null;
    }

    public Boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        if (claims == null) {
            return true;
        }
        return claims.getExpiration().before(new Date());
    }

    public Boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail != null && extractedEmail.equals(email) && !isTokenExpired(token));
    }

    public Boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims != null && !isTokenExpired(token);
        } catch (Exception e) {
            System.err.println("❌ Token validation failed: " + e.getMessage());
            return false;
        }
    }

    public Long getExpirationTime(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.getExpiration().getTime() : null;
    }
}
