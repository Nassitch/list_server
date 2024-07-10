package com.list.server.exceptions;

import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Cette classe centralise la gestion des exceptions de l'application afin de les renvoyer côté front
    // La gestion des exceptions liées au JWT sont elles gérées par le JwtAuthenticationErrors
    // Car les exceptions liées au JWT sont gérées par Spring Security et non par l'application elle-même
    // Et que les exceptions levées dans les filtres de Spring Security ne sont pas interceptées par cette classe

    // Helper
    private ResponseEntity<Map<String, String>> handleException(Exception ex, HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        String errorMessage = ex.getMessage();
        response.put("Error", errorMessage);

        return ResponseEntity.status(status)
                .body(response);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<Map<String, String>> handleUsernameAlreadyTakenException(UsernameAlreadyTakenException exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<Map<String, String>> handleMethodeNotAllowedException(MethodNotAllowedException exception) {
        return handleException(exception, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException exception) {
        return new ResponseEntity<>(exception.getReason(), exception.getStatusCode());
    }

}

