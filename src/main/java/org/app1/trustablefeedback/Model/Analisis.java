package org.app1.trustablefeedback.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Analisis {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(updatable = false, nullable = false)
    private Boolean result;

    @Column(updatable = false, nullable = false)
    private Double accuracy;

    @Column(updatable = false, nullable = false)
    private String iaVersion;

    @Column(updatable = false, nullable = false)
    private String feedbackId;
}
