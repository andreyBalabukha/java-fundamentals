package com.example.jdbc;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> findPopularUsers() {
        String sql = """
            SELECT DISTINCT u.name
            FROM Users u
            WHERE (
                SELECT COUNT(*) FROM Friendships WHERE userid1 = u.id OR userid2 = u.id
            ) > 100
            AND (
                SELECT COUNT(*) FROM Likes WHERE userid = u.id
            ) > 100
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"));
    }
}
