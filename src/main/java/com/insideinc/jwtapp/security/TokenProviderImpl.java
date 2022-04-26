package com.insideinc.jwtapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.models.LoginRequest;
import com.insideinc.jwtapp.repository.UserRepository;
import java.util.Date;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TokenProviderImpl implements TokenProvider {

    @Value("${jwt_secret}")
    private String secret;
    private final ObjectMapper objectMapper;
    private final UserDetailsServiceImpl userDetailsService;

    public TokenProviderImpl(
            ObjectMapper objectMapper, UserDetailsServiceImpl userDetailsService
    ) {
        this.objectMapper = objectMapper;
        this.userDetailsService = userDetailsService;
    }
    @SneakyThrows
    public Token getTokenLogin(LoginRequest loginRequest) {
        User user = userDetailsService.getUser(loginRequest);
        String token = generateToken (user);
        return new Token(token);
    }
    @SneakyThrows
    public User getUserFromToken(Token token) {
        DecodedJWT jwt = decodedJWT(token);
        String userDecoded = jwt.getClaim("user").asString();
        return userDetailsService.getUserFromToken(userDecoded);
    }

    public String validateTokenRetrieveSubject(String token) {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withSubject("Name")
                .withIssuer("jwt-app")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("name").asString();
    }

    private String generateToken(User user) {
        return JWT.create()
                .withSubject("Name")
                .withClaim("name", user.getName())
                .withIssuedAt(new Date())
                .withIssuer("INSIDEINC")
                .sign(Algorithm.HMAC256(secret));
    }

    private DecodedJWT decodedJWT(Token token) {
        return JWT.decode(token.getToken());
    }
}
