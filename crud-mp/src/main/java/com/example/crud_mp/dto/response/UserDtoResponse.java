package com.example.crud_mp.dto.response;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class UserDtoResponse {
    
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String role;

    public UserDtoResponse(UUID id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
