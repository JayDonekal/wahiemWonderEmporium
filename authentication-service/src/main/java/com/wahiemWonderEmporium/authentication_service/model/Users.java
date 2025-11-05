package com.wahiemWonderEmporium.authentication_service.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(value = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Users {

    @Id
    private String userId;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;


}
