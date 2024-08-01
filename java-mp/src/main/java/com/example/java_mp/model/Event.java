package com.example.java_mp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Column(name = "date")
    private ZonedDateTime date;

    public Event(String title, ZonedDateTime date) {
        this.title = title;
        this.date = date;
    }

    public Event() {

    }

}