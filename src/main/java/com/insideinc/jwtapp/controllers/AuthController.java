package com.insideinc.jwtapp.controllers;

import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.models.LoginRequest;
import com.insideinc.jwtapp.repository.UserRepository;
import com.insideinc.jwtapp.security.TokenProvider;
import com.insideinc.jwtapp.security.TokenProviderImpl;
import com.insideinc.jwtapp.security.Token;
import com.insideinc.jwtapp.security.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public AuthController(
           UserDetailsServiceImpl userDetailsServiceImpl,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            TokenProviderImpl tokenProvider
    ) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerHandler(@RequestBody User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPasswordHash(encodedPass);
        userDetailsServiceImpl.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public Token loginHandler(@RequestBody LoginRequest body) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getName(),
                body.getPassword());
        authenticationManager.authenticate(authInputToken);
        return tokenProvider.getTokenLogin(body);
    }
}
