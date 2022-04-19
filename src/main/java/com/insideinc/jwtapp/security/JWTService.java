package com.insideinc.jwtapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.insideinc.jwtapp.entity.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTService {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(User user) {
        return JWT.create().withSubject("Name").withClaim("name", user.getName()).withIssuedAt(new Date())
                .withIssuer("INSIDEINC").sign(Algorithm.HMAC256(secret));
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

    public DecodedJWT decodedJWT(Token token) {
        return JWT.decode(token.getTokenName());
    }
}
