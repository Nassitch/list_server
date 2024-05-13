package com.poec.projet_backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    // Cette classe permet de renvoyer au FRONT les erreurs liées aux problèmes d'accès à une ressource
    // Elle est déclenchée automatiquement par Spring lorsqu'une requête authentifiée essaie d'accéder à une ressource
    // Ça ne fonctionne que pour les ressources protégées par Spring Security (ans le Security Config.

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);

        Map<String, String> error = new HashMap<>();
        error.put("access_denied", "true");
        error.put("error_message", "You do not have sufficient rights to access this resource");

        new ObjectMapper().writeValue(response.getOutputStream(), error);

    }
}
