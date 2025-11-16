package com.wahiemwonderemporium.ordersms.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;


    public String extractEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret)
                .build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    public List<SimpleGrantedAuthority> extractRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret)
                .build().parseClaimsJws(token)
                .getBody();

        // Roles stored in JWT as List<Map<String, String>>
        List<Map<String, String>> roles = claims.get("roles", List.class);

        if (roles == null) {
            return List.of(); // no roles → no authorities
        }

        // Convert authority strings into GrantedAuthority objects
        return roles.stream()
                .map(roleMap -> new SimpleGrantedAuthority(roleMap.get("authority")))
                .toList();
    }


    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Check username and expiration
            boolean isTokenExpired = claims.getExpiration().before(new Date());

            log.info("isTokenExpired: " + isTokenExpired);
            return  !isTokenExpired;

        } catch (Exception e) {
            log.error("Invalid token provided: {}", e.getMessage());
            return false;
        }
    }

}
