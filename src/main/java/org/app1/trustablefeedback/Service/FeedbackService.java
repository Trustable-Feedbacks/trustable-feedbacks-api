package org.app1.trustablefeedback.Service;

import org.app1.trustablefeedback.DTO.Feedback.AnalisisServerRequestDTO;
import org.app1.trustablefeedback.DTO.Feedback.AnalisisServerResponseDTO;
import org.app1.trustablefeedback.DTO.Feedback.HistoryResponseDTO;
import org.app1.trustablefeedback.Model.Analisis;
import org.app1.trustablefeedback.Model.Feedback;
import org.app1.trustablefeedback.Repository.AnalisisRepository;
import org.app1.trustablefeedback.Repository.ClientRepository;
import org.app1.trustablefeedback.Repository.FeedbackRepository;
import org.app1.trustablefeedback.Security.JwtService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FeedbackService {
    FeedbackRepository feedbackRepository;
    ClientRepository clientRepository;
    JwtService jwtService;
    AnalisisRepository analisisRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, ClientRepository clientRepository, JwtService jwtService, AnalisisRepository analisisRepository) {
        this.feedbackRepository = feedbackRepository;
        this.clientRepository = clientRepository;
        this.jwtService = jwtService;
        this.analisisRepository = analisisRepository;
    }

    public HistoryResponseDTO<Feedback> feedbackHistory(String token){
        //get credentials
        final String clientEmail = jwtService.extractUsername(token);
        final String clientID = clientRepository.findIdByEmail(clientEmail).toString();

        //find the feedbacks
        final List<Feedback> listFeedback = feedbackRepository.findAllByClientID(clientID);

        return new HistoryResponseDTO<>(listFeedback);
    }

    public HistoryResponseDTO<Analisis> analisisHistory(String token){
        //getting client email and ID
        final String clientEmail = jwtService.extractUsername(token);
        final String clientId = clientRepository.findIdByEmail(clientEmail).toString();

        //finding the feedbacks by clientId
        final List<Analisis> listAnalisis = analisisRepository.findAllByClientId(clientId);

        return new HistoryResponseDTO<>(listAnalisis);
    }

    public AnalisisServerResponseDTO analyze(AnalisisServerRequestDTO requestDTO){
        //creating the server request to the AI
        HttpHeaders header = new HttpHeaders(); //request header
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AnalisisServerRequestDTO> entity = new HttpEntity<>(requestDTO, header); //body + header

        RestTemplate restTemplate = new RestTemplate();
        String url = "url";

        ResponseEntity<AnalisisServerResponseDTO> responseEntity = restTemplate.postForEntity(
                url, entity, AnalisisServerResponseDTO.class
        );

        //returning the response
        return responseEntity.getBody();
    }
}
