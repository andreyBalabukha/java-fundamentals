package com.microservice.micro_recipient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.micro_recipient.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
