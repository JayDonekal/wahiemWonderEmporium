package com.wahiemWonderEmporium.authentication_service.utils;

import com.wahiemWonderEmporium.authentication_service.model.Users;
import com.wahiemWonderEmporium.authentication_service.model.viewModel.UsersRequest;
import com.wahiemWonderEmporium.authentication_service.model.viewModel.UsersResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Converters {

    public static Users userRequestDTOtoUsers(UsersRequest usersRequest){
        Users users = Users.builder()
                .email(usersRequest.getEmail())
                .username(usersRequest.getUsername())
                .firstName(usersRequest.getFirstName())
                .lastName(usersRequest.getLastName())
                .password(usersRequest.getPassword())
                .build();
        return users;
    }

    public static UsersResponse usersDTOtoUsersResponse(Users users){

        UsersResponse response  = UsersResponse.builder()
                    .userId(users.getUserId())
                    .email(users.getEmail())
                    .username(users.getUsername())
                    .firstName(users.getFirstName())
                    .lastName(users.getLastName())
                    .password(users.getPassword())
                    .roles(users.getRoles())
                    .build();

        return response;
    }

}
