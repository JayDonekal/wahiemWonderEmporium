package com.wahiemWonderEmporium.authentication_service.controller;

import com.wahiemWonderEmporium.authentication_service.model.Users;
import com.wahiemWonderEmporium.authentication_service.model.security.AuthResponse;
import com.wahiemWonderEmporium.authentication_service.model.security.LoginRequest;
import com.wahiemWonderEmporium.authentication_service.model.viewModel.UsersRequest;
import com.wahiemWonderEmporium.authentication_service.repository.UserRepository;
import com.wahiemWonderEmporium.authentication_service.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wahiemWonderEmporium.authentication_service.utils.Converters.userRequestDTOtoUsers;

@RestController
@RequestMapping("/api/jwt")
@Slf4j
@AllArgsConstructor
public class TokensController {

   public final JwtUtil jwtUtil;
   public final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(user)));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UsersRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("User already exists");
        }
        Users user = userRequestDTOtoUsers(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(user)));
    }

}
