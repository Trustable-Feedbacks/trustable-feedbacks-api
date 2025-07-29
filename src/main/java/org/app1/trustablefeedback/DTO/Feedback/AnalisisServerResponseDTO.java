package org.app1.trustablefeedback.DTO.Feedback;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnalisisServerResponseDTO {
    private final Boolean isFair;
    private final Double accuracy;
    private final String aiVersion;
    private final Double precision;
}
