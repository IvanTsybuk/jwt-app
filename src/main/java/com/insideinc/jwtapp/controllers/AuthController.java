package com.insideinc.jwtapp.controllers;

import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.models.LoginRequest;
import com.insideinc.jwtapp.repository.UserRepository;
import com.insideinc.jwtapp.security.RegistrationResponse;
import com.insideinc.jwtapp.security.RegistrationResponseProvider;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RegistrationResponseProvider responseProvider;

    public AuthController(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RegistrationResponseProvider responseProvider
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.responseProvider = responseProvider;
    }

    @PostMapping("/register")
    public RegistrationResponse registerHandler(@RequestBody User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPasswordHash(encodedPass);
        userRepository.save(user);
        return responseProvider.getToken(user.getName());
    }

    @PostMapping("/login")
    public RegistrationResponse loginHandler(@RequestBody LoginRequest body) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getName(),
                body.getPassword());
        authenticationManager.authenticate(authInputToken);
        return responseProvider.getToken(body.getName());
    }
}
