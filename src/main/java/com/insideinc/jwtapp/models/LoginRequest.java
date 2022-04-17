package com.insideinc.jwtapp.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    private String name;
    private String password;
}
