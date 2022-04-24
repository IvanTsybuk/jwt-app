package com.insideinc.jwtapp.security;

import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final String ABSENT_USER_MESSAGE = "Could not findUser with userName = ";
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName)
                .orElseThrow(()-> new UsernameNotFoundException(ABSENT_USER_MESSAGE + userName));
        return new org.springframework.security.core.userdetails.User(
                userName,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
