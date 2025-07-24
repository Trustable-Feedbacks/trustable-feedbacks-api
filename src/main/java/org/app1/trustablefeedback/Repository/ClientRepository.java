package org.app1.trustablefeedback.Repository;

import org.app1.trustablefeedback.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findClientByEmail(String email);
}
