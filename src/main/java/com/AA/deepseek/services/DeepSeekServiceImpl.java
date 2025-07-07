package com.AA.deepseek.services;

import com.AA.deepseek.Client.DeepSeekApiClient;
import com.AA.deepseek.dto.DeepSeekRequest;
import com.AA.deepseek.dto.DeepSeekResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeepSeekServiceImpl implements DeepSeekService {
    private DeepSeekApiClient apiClient;

    private final String apiUrl;
    private final String apiKey;
    private final RestTemplate restTemplate;

    @Autowired
    public DeepSeekServiceImpl(
            RestTemplate restTemplate,
            @Value("${deepseek.api.url}") String apiUrl,
            @Value("${deepseek.api.key}") String apiKey,
            DeepSeekApiClient apiClient
    ) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.apiClient = apiClient;
    }


    @Override
    public DeepSeekResponse getAnswer(String userQuestion) {
        DeepSeekRequest request = new DeepSeekRequest();
        request.setMessages(List.of(
                new DeepSeekRequest.ChatMessage("user", userQuestion)
        ));

        return apiClient.askQuestion(request);
    }
}