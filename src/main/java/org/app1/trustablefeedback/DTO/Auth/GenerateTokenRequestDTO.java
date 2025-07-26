package org.app1.trustablefeedback.DTO.Auth;

import lombok.Getter;

@Getter
public class GenerateTokenRequestDTO {
    private String email;
    private String password;
}
