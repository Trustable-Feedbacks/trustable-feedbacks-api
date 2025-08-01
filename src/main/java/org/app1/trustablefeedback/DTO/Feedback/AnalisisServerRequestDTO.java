package org.app1.trustablefeedback.DTO.Feedback;

import lombok.*;


@AllArgsConstructor
@Getter
public class AnalisisServerRequestDTO {
    private final String text;
    private final Integer grade;
}
