package com.wahiemWonderEmporium.authentication_service.model.viewModel;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersRequest {
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
