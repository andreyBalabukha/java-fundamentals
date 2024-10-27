package com.microservice.micro_sender.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    private final RabbitTemplate rabbitTemplate;

    public NotificationController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/notification")
    public void sendNotification(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        try {
            rabbitTemplate.convertAndSend("exchangeName", "routingKey", message);
            logger.info("Message sent to queue: ", message);
        } catch (Exception e) {
            logger.error("Failed to send message: ", e.getMessage());
        }
    }
}