package com.list.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAuthenticationErrors implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (response.getStatus() == HttpStatus.UNAUTHORIZED.value()) {
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setHeader("error", exception.getMessage());

            Map<String, String> error = new HashMap<>();

            if (request.getAttribute("expired_exception") != null) {
                error.put("is_token_expired", "true");
                error.put("error_message", "JWT has expired. Please log in again.");
            }
            if (request.getAttribute("malformed_exception") != null) {
                error.put("is_jwt_malformed", "true");
                error.put("error_message", "JWT is malformed. Please verify its integrity.");
            }
            if (request.getAttribute("jwt_exception") != null) {
                error.put("is_jwt_exception", "true");
                error.put("error_message", "");
            }
            if (request.getAttribute("no_jwt_provided") != null) {
                error.put("no_jwt_provided", "true");
                error.put("error_message", "No JWT provided.");
            }

            new ObjectMapper().writeValue(response.getOutputStream(), error);
        } else {
            response.sendError(response.getStatus());
        }
    }

}
