package org.app1.trustablefeedback.Service;

import org.app1.trustablefeedback.ClientDetails.ClientDetails;
import org.app1.trustablefeedback.ClientDetails.ClientDetailsService;
import org.app1.trustablefeedback.Model.Client;
import org.app1.trustablefeedback.Repository.ClientRepository;
import org.app1.trustablefeedback.Security.JwtService;
import org.app1.trustablefeedback.Security.Role;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ClientDetailsService clientDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    public AuthService(JwtService jwtService, AuthenticationManager authenticationManager, ClientDetailsService clientDetailsService, PasswordEncoder passwordEncoder, ClientRepository clientRepository) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.clientDetailsService = clientDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
    }

    public String generateToken(String email, String senha) throws BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha)
        );//exeption handled on the controller

        //encontrando o usuario
        ClientDetails clientDetails = clientDetailsService.loadUserByUsername(email);

        return jwtService.generateToken(clientDetails);
    }

    public void registerUser(String email, String password, String roleString)
        throws IllegalArgumentException, NullPointerException, DataIntegrityViolationException, ConstraintViolationException {

        //global variants
        Role role;
        Client client;

        try{
             role = Role.valueOf(roleString.toUpperCase());
             client = new Client();
        }catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("Invalid Role");
        }//NullPointer handled on Controller



        client.setEmail(email);
        client.setRole(role);
        client.setCreationDate(new Date());
        client.setPassword(passwordEncoder.encode(password));


        clientRepository.save(client);
    }

    public Boolean validateToken(String token){
        ClientDetails clientFound = clientDetailsService.loadUserByUsername(jwtService.extractUsername(token));
        return jwtService.validateToken(token, clientFound);
    }
}
