package com.example.java_mp.dto.response;

import lombok.Getter;
import lombok.Setter;

public class TicketDtoRefactor {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long eventId;

    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    private String place;
}
