package com.example.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Random;

@Component
public class Data {

    private final JdbcTemplate jdbcTemplate;

    public Data(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        createTables();
        populateData();
    }

    private void createTables() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Users (id INT PRIMARY KEY, name VARCHAR(255), surname VARCHAR(255), birthdate DATE)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Friendships (userid1 INT, userid2 INT, timestamp TIMESTAMP)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Posts (id INT PRIMARY KEY, userId INT, text VARCHAR(255), timestamp TIMESTAMP)");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Likes (postid INT, userid INT, timestamp TIMESTAMP)");
    }

    private void populateData() {
        Random random = new Random();

        for (int i = 1; i <= 10000; i++) {
            jdbcTemplate.update("INSERT INTO Users (id, name, surname, birthdate) VALUES (?, ?, ?, ?)",
                    i, "Name" + i, "Surname" + i, LocalDate.of(1990, 1, 1).plusDays(random.nextInt(10000)));
        }

        for (int i = 0; i < 100000; i++) {
            int userId1 = random.nextInt(1000) + 1;
            int userId2 = random.nextInt(1000) + 1;
            if (userId1 != userId2) {
                jdbcTemplate.update("INSERT INTO Friendships (userid1, userid2, timestamp) VALUES (?, ?, ?)",
                        userId1, userId2, Timestamp.valueOf("2025-03-15 10:00:00"));
            }
        }

        for (int i = 1; i <= 10000; i++) {
            jdbcTemplate.update("INSERT INTO Posts (id, userId, text, timestamp) VALUES (?, ?, ?, ?)",
                    i, random.nextInt(1000) + 1, "Sample Post " + i, Timestamp.valueOf("2025-03-15 10:00:00"));
        }

        for (int i = 0; i < 300000; i++) {
            int postId = random.nextInt(1000) + 1;
            int userId = random.nextInt(1000) + 1;
            jdbcTemplate.update("INSERT INTO Likes (postid, userid, timestamp) VALUES (?, ?, ?)",
                    postId, userId, Timestamp.valueOf("2025-03-15 10:00:00"));
        }
    }
}