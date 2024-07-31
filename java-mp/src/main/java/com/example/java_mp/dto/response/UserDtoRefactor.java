package com.example.java_mp.dto.response;

import lombok.Getter;
import lombok.Setter;

public class UserDtoRefactor {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;
}
