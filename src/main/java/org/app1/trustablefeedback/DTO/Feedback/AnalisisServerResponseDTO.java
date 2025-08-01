package org.app1.trustablefeedback.DTO.Feedback;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AnalisisServerResponseDTO {
    private final Boolean is_fair;
    private final Double chance;
    private final String ai_version;
    private final Double accuracy;
}
