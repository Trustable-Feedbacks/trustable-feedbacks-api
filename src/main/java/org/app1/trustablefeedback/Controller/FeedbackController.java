package org.app1.trustablefeedback.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.app1.trustablefeedback.DTO.Feedback.HistoryResponseDTO;
import org.app1.trustablefeedback.Model.Analisis;
import org.app1.trustablefeedback.Model.Feedback;
import org.app1.trustablefeedback.Service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/history/{filter}")
    public ResponseEntity<?> feedbackHistory(HttpServletRequest request,
                                                                        @PathVariable String filter){
        //getting the token
        final String header = request.getHeader("Authorization");
        final String token = header.substring(7);

        //taking the filter
        switch (filter.toLowerCase()){
            case "feedback":
                HistoryResponseDTO<Feedback> returnFeedback = feedbackService.feedbackHistory(token);
                return ResponseEntity.status(HttpStatus.OK).body(returnFeedback);
            case "analisis":
                HistoryResponseDTO<Analisis> returnAnalisis = feedbackService.analisisHistory(token);
                return ResponseEntity.status(HttpStatus.OK).body(returnAnalisis);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Filter type not found: " + filter);
        }
    }
}
