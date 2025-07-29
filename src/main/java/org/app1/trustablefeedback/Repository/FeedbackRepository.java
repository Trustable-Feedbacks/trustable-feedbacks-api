package org.app1.trustablefeedback.Repository;

import org.app1.trustablefeedback.Model.Client;
import org.app1.trustablefeedback.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<UUID, Feedback> {
    @Query("select f from Feedback f where f.clientID= :clientID")
    List<Feedback> findAllByClientID(@Param("clientID") String clientID);
}
