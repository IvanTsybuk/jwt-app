package com.insideinc.jwtapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insideinc.jwtapp.exceptions.ExceptionResponseDto;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponseDto> handleAuthenticationException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponseDto(e.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ExceptionResponseDto> handleJsonProcessingException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponseDto(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleUsernameNotFoundException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponseDto(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
