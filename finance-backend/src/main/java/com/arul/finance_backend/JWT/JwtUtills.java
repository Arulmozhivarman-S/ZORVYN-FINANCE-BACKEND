package com.arul.finance_backend.JWT;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.arul.finance_backend.enums.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtills {
    
    private static final String secret = "WERTYUIOASDFGHJKL";

    public String generateToken(Long userId, UserRole userRole){

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) 
                .claim("role", userRole.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}