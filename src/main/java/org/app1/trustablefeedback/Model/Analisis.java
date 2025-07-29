package org.app1.trustablefeedback.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Analisis {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(updatable = false, nullable = false)
    private EnumResult result;

    @Column(updatable = false, nullable = false)
    private Double accuracy;

    @Column(updatable = false, nullable = false)
    private String iaVersion;

    @Column(updatable = false, nullable = false)
    private String feedbackId;
}
