package com.microservice.micro_recipient.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microservice.micro_recipient.model.Message;
import com.microservice.micro_recipient.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RabbitListener(queues = "queueName")
    public void receiveMessage(String messageContent) {
        Message message = new Message();
        message.setContent(messageContent);
        messageRepository.save(message);
        logger.info("Received and saved message: {}", messageContent);
    }

    @Scheduled(fixedRate = 5000) // N seconds
    public void logMessages() {
        List<Message> messages = messageRepository.findAll();
        logger.info("Current messages: {}", messages);
    }
}