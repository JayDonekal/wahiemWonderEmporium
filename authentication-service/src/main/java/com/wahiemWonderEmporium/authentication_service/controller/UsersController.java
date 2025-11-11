package com.wahiemWonderEmporium.authentication_service.controller;

import com.wahiemWonderEmporium.authentication_service.model.Users;
import com.wahiemWonderEmporium.authentication_service.model.viewModel.UsersRequest;
import com.wahiemWonderEmporium.authentication_service.model.viewModel.UsersResponse;
import com.wahiemWonderEmporium.authentication_service.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wahiemWonderEmporium.authentication_service.utils.Converters.userRequestDTOtoUsers;
import static com.wahiemWonderEmporium.authentication_service.utils.Converters.usersDTOtoUsersResponse;

@RestController
@RequestMapping("/api/users")

@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    private ResponseEntity<UsersResponse> createNewUser(@RequestBody UsersRequest usersRequest) {
// TODO : Check if user is not in the system before adding anything in.
        log.info("creating New User {}", usersRequest);
        Users user = userRequestDTOtoUsers(usersRequest);
        user.setRoles(List.of("USER"));
        UsersResponse usersResponse = usersDTOtoUsersResponse(usersService.createNewUser(user));

        return new ResponseEntity<>(usersResponse, HttpStatus.CREATED);
    }


    //Never expose the admin user creation. This is only internal. Only Admins can create Admins, hence the duplication.
    @PostMapping("/admin")
    private ResponseEntity<UsersResponse> createNewAdminUser(@RequestBody UsersRequest usersRequest) {

        log.info("creating New User {}", usersRequest);
        Users user = userRequestDTOtoUsers(usersRequest);
        user.setRoles(List.of("ADMIN"));
        UsersResponse usersResponse = usersDTOtoUsersResponse(usersService.createNewUser(user));

        return new ResponseEntity<>(usersResponse, HttpStatus.CREATED);
    }


    @GetMapping("/username/{username}")
    private ResponseEntity<UsersResponse> getUserByUsername(@PathVariable String username) {
        log.info("retrieving New User by username {}", username);

        Users user = usersService.retrieveUserByUsername(username);

        return new ResponseEntity<>(usersDTOtoUsersResponse(user), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    private ResponseEntity<UsersResponse> getUserByEmail(@PathVariable String email) {
        log.info("retrieving New User by email {}", email);

        Users user = usersService.retrieveUserByEmail(email);

        return new ResponseEntity<>(usersDTOtoUsersResponse(user), HttpStatus.OK);
    }


}
