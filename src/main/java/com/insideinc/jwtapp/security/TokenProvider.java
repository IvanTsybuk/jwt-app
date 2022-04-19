package com.insideinc.jwtapp.security;

import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.models.LoginRequest;

public interface TokenProvider {
    Token getTokenLogin(LoginRequest loginRequest);
    User getUserFromToken(Token token);
}
