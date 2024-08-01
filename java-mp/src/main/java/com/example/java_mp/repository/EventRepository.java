package com.example.java_mp.repository;
import com.example.java_mp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM events WHERE date > :eventDate", nativeQuery = true)
    public List<Event> findEventsAfterDate(@Param("eventDate") ZonedDateTime eventDate);
}
