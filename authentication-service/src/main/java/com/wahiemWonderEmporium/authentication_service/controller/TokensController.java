package com.wahiemWonderEmporium.authentication_service.controller;

import com.wahiemWonderEmporium.authentication_service.model.security.AuthResponse;
import com.wahiemWonderEmporium.authentication_service.model.security.LoginRequest;
import com.wahiemWonderEmporium.authentication_service.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jwt")
@Slf4j
@AllArgsConstructor
public class TokensController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
       String token = jwtService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }


}
