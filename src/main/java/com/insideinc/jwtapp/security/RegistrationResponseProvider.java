package com.insideinc.jwtapp.security;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class RegistrationResponseProvider {

    private final JWTUtil jwtUtil;

    public RegistrationResponseProvider(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @SneakyThrows
    public RegistrationResponse getToken(String str) {
        String token = jwtUtil.generateToken(str);
        return new RegistrationResponse(token);
    }
}
