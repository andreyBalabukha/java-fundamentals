package com.java.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class InfoController {
    private final Random random = new Random();

    @GetMapping("/info")
    public String getRandomStats() {
        return "Random stats: " + random.nextInt(100);
    }

    @GetMapping("/about")
    public String about() {
        return "About this MVC application.";
    }
}