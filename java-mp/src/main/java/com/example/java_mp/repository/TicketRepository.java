package com.example.java_mp.repository;
import com.example.java_mp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Event, Long> {
}
