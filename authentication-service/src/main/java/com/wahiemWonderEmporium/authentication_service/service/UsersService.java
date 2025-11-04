package com.wahiemWonderEmporium.authentication_service.service;

import com.wahiemWonderEmporium.authentication_service.model.Users;
import com.wahiemWonderEmporium.authentication_service.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
@Slf4j
public class UsersService {

    private final UserRepository userRepository;


    public Users createNewUser(Users users) {
        Users savedUser;

        try {
            savedUser = userRepository.save(users);
            log.info("User created {}", savedUser);
        }
        catch (DataAccessException e) {
            throw new DataAccessException("User could not be created in DB", e) {
            };
        }
        return savedUser;
    }

    public Optional<Users> retrieveUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

}
