package com.wahiemWonderEmporium.authentication_service.security;

import com.wahiemWonderEmporium.authentication_service.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(Users user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("roles", user.getRoles());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret)
                .build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Claims extractClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret)
                .build().parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            log.error("Invalid token provided {}", e.getMessage());
            return false;
        }
    }

}
