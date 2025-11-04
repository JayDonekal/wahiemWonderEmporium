package com.wahiemWonderEmporium.authentication_service.model.viewModel;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsersResponse {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;

}
