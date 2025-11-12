package com.wahiemWonderEmporium.authentication_service.service.security;

import com.wahiemWonderEmporium.authentication_service.model.Users;
import com.wahiemWonderEmporium.authentication_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetailsService {


    private UserRepository userRepository;

    private UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

}
