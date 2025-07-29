package org.app1.trustablefeedback.Repository;

import org.app1.trustablefeedback.Model.Analisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AnalisisRepository extends JpaRepository<Analisis, UUID> {
    @Query("select a from Analisis as a join Feedback as f on f.id = a.feedbackId where f.clientID = :clientId")
    List<Analisis> findAllByClientId(@Param("clientId") String clientId);
}
