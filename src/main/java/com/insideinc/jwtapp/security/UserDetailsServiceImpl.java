package com.insideinc.jwtapp.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.models.LoginRequest;
import com.insideinc.jwtapp.repository.UserRepository;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetails {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserDetailsServiceImpl(
            UserRepository userRepository, ObjectMapper objectMapper
    ) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUser(LoginRequest loginRequest) {
        return userRepository.findByName(objectMapper.convertValue(loginRequest, User.class).getName())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
    }

    public User getUserFromToken(String userDecoded) throws JsonProcessingException {
        return objectMapper.readValue(userDecoded, User.class);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
