package com.list.server.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    // Ces routes sont protégées globalement via le fichier SecurityConfig
    @GetMapping("/users-only")
    public ResponseEntity<String> getUsersOnly() {
        return ResponseEntity.ok("OUAIS ACCESSIBLE QUE PAR UN ROLE_USER OMG");
    }

    @GetMapping("/admin-only")
    public ResponseEntity<String> getAdminsOnly() {
        return ResponseEntity.ok("OUAIS ACCESSIBLE QUE PAR UN ROLE_ADMIN");
    }
}
