package com.insideinc.jwtapp.controllers;

import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.security.Token;
import com.insideinc.jwtapp.security.TokenProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final TokenProvider tokenProvider;

    public UserController(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/info")
    public User getUsersInfo() {
        Token token = (Token) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return tokenProvider.getUserFromToken(token);
    }
}
