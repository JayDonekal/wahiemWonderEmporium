package com.wahiemWonderEmporium.authentication_service.model.security;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
