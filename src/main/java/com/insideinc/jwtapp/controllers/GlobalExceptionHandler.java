package com.insideinc.jwtapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinc.jwtapp.exceptions.ExceptionResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ExceptionResponse> handleJsonProcessingException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUsernameNotFoundException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
