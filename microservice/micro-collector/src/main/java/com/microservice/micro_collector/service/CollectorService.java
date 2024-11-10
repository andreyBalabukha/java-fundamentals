package com.microservice.micro_collector.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class CollectorService {

    private static final Logger logger = LoggerFactory.getLogger(CollectorService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String RECIPIENT_URL = "http://micro-recipient:8082/message";

    @Scheduled(fixedRate = 10000)
    public void collectMessages() {
        try {
            List<String> messages = restTemplate.getForObject(RECIPIENT_URL, List.class);
            logger.info("Collected messages: ", messages);
        } catch (Exception e) {
            logger.warn("Failed to collect messages: ", e.getMessage());
        }
    }
}