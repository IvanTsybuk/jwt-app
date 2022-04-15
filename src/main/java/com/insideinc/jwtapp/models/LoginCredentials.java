package com.insideinc.jwtapp.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginCredentials {

    private String email;
    private String password;
}
