package org.app1.trustablefeedback.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Analyze {
    @Id
    @GeneratedValue
    UUID id;

    @Column(updatable = false, nullable = false)
    EnumResult result;

    @Column(updatable = false, nullable = false)
    Double accuracy;

    @Column(updatable = false, nullable = false)
    String iaVersion;
}
