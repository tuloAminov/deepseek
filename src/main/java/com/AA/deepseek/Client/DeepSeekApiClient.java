package com.AA.deepseek.Client;

import com.AA.deepseek.dto.DeepSeekRequest;
import com.AA.deepseek.dto.DeepSeekResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;


@Slf4j
@Service
public class DeepSeekApiClient {

    private final String apiUrl;
    private final String apiKey;
    private final RestTemplate restTemplate;

    @Autowired
    public DeepSeekApiClient(@Value("${deepseek.api.url}") String apiUrl,
                             @Value("${deepseek.api.key}") String apiKey,
                             RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;

        validateConfiguration();
    }

    private void validateConfiguration() {
        if (!apiUrl.startsWith("https://")) {
            throw new IllegalStateException("API URL must use HTTPS");
        }
        if (apiKey.isBlank()) {
            throw new IllegalStateException("API key cannot be blank");
        }
    }

    public DeepSeekResponse askQuestion(DeepSeekRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DeepSeekRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<DeepSeekResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    entity,
                    DeepSeekResponse.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("API request failed with status: {}", response.getStatusCode());
                throw new RuntimeException("DeepSeek API returned non-success status");
            }

            return response.getBody();
        } catch (RestClientException e) {
            log.error("API request to DeepSeek failed", e);
            throw new RuntimeException("Failed to communicate with DeepSeek API", e);
        }
    }
}