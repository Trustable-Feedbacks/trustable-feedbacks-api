package org.app1.trustablefeedback.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, updatable = false)
    private Integer grade;

    @Column(nullable = false, updatable = false)
    private String clientID;

    @CreationTimestamp
    @Column(updatable = false)
    private Date date;

    @Column(nullable = false, updatable = false)
    private String content;
}
