package com.example.crud_mp.dto.request;

import lombok.Getter;
import lombok.Setter;

public class UserDtoRequest {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String role;
}
