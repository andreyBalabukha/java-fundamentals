package com.microservice.micro_recipient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.micro_recipient.model.Message;
import com.microservice.micro_recipient.repository.MessageRepository;

import java.util.List;

@RestController
public class RecipientController {

    private final MessageRepository messageRepository;
    private static final Logger logger = LoggerFactory.getLogger(RecipientController.class);

    public RecipientController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/message")
    public List<Message> getMessages() {
        List<Message> messages = messageRepository.findAll();
        messageRepository.deleteAll();
        logger.info("Returned and cleared messages: {}", messages);
        return messages;
    }
}