package org.app1.trustablefeedback.Controller;

import org.app1.trustablefeedback.DTO.GenerateTokenRequestDTO;
import org.app1.trustablefeedback.DTO.RegisterUserRequestDTO;
import org.app1.trustablefeedback.DTO.ValidateTokenRequestDTO;
import org.app1.trustablefeedback.Service.AuthService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody GenerateTokenRequestDTO dto){
        try{
            String token = authService.generateToken(dto.getEmail(), dto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }catch (BadCredentialsException bde){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bde.getMessage());
        }catch (InternalAuthenticationServiceException iase){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(iase.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserRequestDTO dto){
        try{
            authService.registerUser(dto.getEmail(), dto.getPassword(), dto.getRole());
            return ResponseEntity.status(HttpStatus.OK).body("USER CREATED");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }catch (NullPointerException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nullPointerException");
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }catch (ConstraintViolationException exception){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestBody ValidateTokenRequestDTO dto){
        try {
            Boolean status = authService.validateToken(dto.getToken());
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(Boolean.FALSE);
        }
    }
}
