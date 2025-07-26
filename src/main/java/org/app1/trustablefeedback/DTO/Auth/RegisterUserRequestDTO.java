package org.app1.trustablefeedback.DTO.Auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDTO {
    private String email;
    private String password;
    private String role;
}
