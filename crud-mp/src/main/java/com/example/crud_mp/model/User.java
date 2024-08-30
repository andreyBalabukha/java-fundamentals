package com.example.crud_mp.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @javax.persistence.Id
    @Id
    private UUID id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "role")
    private String role;

    @Column(nullable = false, name = "password")
    private String password;

    public User(String username, String email, String role, String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
