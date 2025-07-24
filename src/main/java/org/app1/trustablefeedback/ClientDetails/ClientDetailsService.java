package org.app1.trustablefeedback.ClientDetails;

import org.app1.trustablefeedback.Model.Client;
import org.app1.trustablefeedback.Repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;

    public ClientDetailsService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Client findedClient = clientRepository.findClientByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new ClientDetails(findedClient);
    }
}
