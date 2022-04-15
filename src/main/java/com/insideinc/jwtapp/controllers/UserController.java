package com.insideinc.jwtapp.controllers;

import com.insideinc.jwtapp.entity.User;
import com.insideinc.jwtapp.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/info")
    public User getUsersInfo() {
      String email = (String) SecurityContextHolder
              .getContext()
              .getAuthentication()
              .getPrincipal();
      return userRepo.findByEmail(email).get();
    }
}
