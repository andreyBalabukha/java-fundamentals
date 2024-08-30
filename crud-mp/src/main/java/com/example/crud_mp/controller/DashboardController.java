package com.example.crud_mp.controller;

import com.example.crud_mp.CrudMpApplication;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DashboardController {

    Logger logger = LoggerFactory.getLogger(CrudMpApplication.class);

    @PreAuthorize("hasAnyRole('user','admin')")
    @GetMapping("/welcome-message")
    public ResponseEntity<String> getFirstWelcomeMessage(Authentication authentication){
        logger.info("getFirstWelcomeMessage authentication: " + authentication);
        return ResponseEntity.ok("Welcome to the JWT Tutorial:"+authentication.getName()+"with scope:"+authentication.getAuthorities());
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/manager-message")
    public ResponseEntity<String> getManagerData(Principal principal){
        return ResponseEntity.ok("Manager::"+principal.getName());

    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/admin-message")
    public ResponseEntity<String> getAdminData(@RequestParam("message") String message, Principal principal){
        return ResponseEntity.ok("Admin::"+principal.getName()+" has this message:"+message);

    }

}
