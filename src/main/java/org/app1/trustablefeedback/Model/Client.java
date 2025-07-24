package org.app1.trustablefeedback.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.app1.trustablefeedback.Security.Role;

import java.util.Date;
import java.util.UUID;

@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue
    UUID id;

    @Column(unique = true)
    String email;

    String password;
    Date creationDate;

    Role role;
}
