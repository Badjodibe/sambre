package com.sambre.sambre.config.messaging.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SmsService {
    @Value("${smsvas.url}")
    private String smsApiUrl;

    @Value("${smsvas.token}")
    private String token;

    @Value("${smsvas.sender}")
    private String sender;

    private final RestTemplate restTemplate = new RestTemplate();

    public String sendSms(String phoneNumber, String message) {
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("to", phoneNumber);
        params.put("text", message);
        params.put("from", sender);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        StringBuilder body = new StringBuilder();
        params.forEach((k, v) -> body.append(k).append("=").append(v).append("&"));

        HttpEntity<String> requestEntity = new HttpEntity<>(body.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                smsApiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response.getBody();
    }
}
