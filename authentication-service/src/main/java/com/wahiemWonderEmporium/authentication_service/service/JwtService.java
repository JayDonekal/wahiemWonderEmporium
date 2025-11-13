package com.wahiemWonderEmporium.authentication_service.service;


import com.wahiemWonderEmporium.authentication_service.model.security.LoginRequest;
import com.wahiemWonderEmporium.authentication_service.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtService {
;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public String login (LoginRequest request) {
        try {
           org.springframework.security.core.Authentication authenticate =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            return jwtUtil.generateToken(userDetails);
        } catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

}
