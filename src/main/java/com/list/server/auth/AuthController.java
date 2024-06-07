package com.list.server.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register-log")
    public ResponseEntity<Map<String, String>> registerLog(@RequestBody RegisterLogRequest request, HttpServletRequest httpRequest) throws Exception {
        return ResponseEntity.ok(service.registerLog(request, httpRequest));
    }

    @PostMapping("/register-user")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterUserRequest request, HttpServletRequest httpRequest) throws Exception {
        return ResponseEntity.ok(service.registerUser(request, httpRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request, HttpServletRequest httpRequest) {
        AuthResponse authenticationResponse = service.authenticate(request, httpRequest);
        return ResponseEntity.ok(authenticationResponse);

    }
}
