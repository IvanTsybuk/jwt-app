package com.insideinc.jwtapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.models.LoginRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class TokenProviderImplementation implements TokenProvider {

    private final JWTService jwtService;
    private final ObjectMapper objectMapper;

    public TokenProviderImplementation(JWTService jwtService, ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }
    @SneakyThrows
    public Token getTokenLogin(LoginRequest loginRequest) {
        String token = jwtService.generateToken(objectMapper.convertValue(loginRequest, User.class));
        return new Token(token);
    }
    @SneakyThrows
    public User getUserFromToken(Token token) {
        final DecodedJWT jwt = jwtService.decodedJWT(token);
        return objectMapper.readValue(jwt.getClaim("user").asString(), User.class);
    }
}
