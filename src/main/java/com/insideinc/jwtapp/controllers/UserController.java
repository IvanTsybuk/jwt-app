package com.insideinc.jwtapp.controllers;

import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.security.Token;
import com.insideinc.jwtapp.security.TokenProviderImplementation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final TokenProviderImplementation responseProvider;

    public UserController(TokenProviderImplementation responseProvider) {
        this.responseProvider = responseProvider;
    }

    @GetMapping("/info")
    public User getUsersInfo() {
        Token principal = (Token) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return responseProvider.getUserFromToken(principal);
    }
}
