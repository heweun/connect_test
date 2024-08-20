package com.example.test_spring.classification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class ClassController {
    @PostMapping("/classify")
    public ResponseEntity<String> classifyReview(@RequestBody Map<String, String> payload) {
        try {
            String review = payload.get("review");

            System.out.println("Received review: " + review);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // MultiValueMap을 사용하여 폼 데이터를 설정
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("review", review);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            String fastApiUrl = "http://localhost:8007/classification";
            ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl, request, String.class);
            System.out.println("request: " + request);

            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (RestClientException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while calling FastAPI: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("General error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
