package com.wahiemWonderEmporium.authentication_service.service;

import com.wahiemWonderEmporium.authentication_service.model.Users;
import com.wahiemWonderEmporium.authentication_service.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
@Slf4j
public class UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Users createNewUser(Users users) {
        Users savedUser;
        if(userRepository.existsByUsername(users.getUsername())) {
            throw new DataAccessException("Username already exists!") {
            };
        }
        if(userRepository.existsByEmail(users.getEmail())) {
            throw new DataAccessException("Email already exists!") {
            };
        }
        try {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            savedUser = userRepository.save(users);
            log.info("User created {}", savedUser);
        }
        catch (DataAccessException e) {
            throw new DataAccessException("User could not be created in DB", e) {
            };
        }
        return savedUser;
    }

    public Users retrieveUserByUsername(String userName) {

        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new DataAccessException("User not found") {});
    }

    public Users retrieveUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new DataAccessException("User not found") {});
    }

}
