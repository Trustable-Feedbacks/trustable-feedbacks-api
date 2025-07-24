package org.app1.trustablefeedback.ClientDetails;

import org.app1.trustablefeedback.Model.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class ClientDetails implements UserDetails {
    private final Client client;

    public ClientDetails(Client client){
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_"+client.getRole().name()));
    }

    @Override
    public String getPassword(){
        return client.getPassword();
    }

    @Override
    public String getUsername(){
        return client.getEmail(); //email passado como username
    }

    @Override
    public boolean isAccountNonExpired (){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
