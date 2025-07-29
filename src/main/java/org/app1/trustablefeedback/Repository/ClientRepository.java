package org.app1.trustablefeedback.Repository;

import org.app1.trustablefeedback.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findClientByEmail(String email);

    @Query("select c.id from Client c where c.email = :email")
    Optional<String> findIdByEmail(@Param("email") String email);
}
