package org.app1.trustablefeedback.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;

@Entity
public class Feedback {
    @Id
    @GeneratedValue
    UUID id;

    @Column(nullable = false)
    Double grade;

    String authorId;

    String clientID;

    @CreationTimestamp
    @Column(updatable = false)
    Date date;
}
