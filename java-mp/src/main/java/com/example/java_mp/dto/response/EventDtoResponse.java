package com.example.java_mp.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class EventDtoResponse {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Date date;
}
