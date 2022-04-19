package com.insideinc.jwtapp.security;

import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userRes = userRepository.findByName(userName);
        if (userRes.isEmpty()) {
            throw new UsernameNotFoundException("Could not findUser with userName = " + userName);
        }
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                userName,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
