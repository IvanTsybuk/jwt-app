package com.insideinc.jwtapp.models;

import lombok.*;

@Data
public class LoginRequest {

    private String name;
    private String password;
}
