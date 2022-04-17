package com.insideinc.jwtapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String name) throws JsonProcessingException {
        return
                JWT.create()
                .withSubject("Name")
                .withClaim("name", name)
                .withIssuedAt(new Date())
                .withIssuer("INSIDEINC")
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenRetrieveSubject(String token) {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("Name")
                .withIssuer("jwt-app")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("name").asString();
    }
}
