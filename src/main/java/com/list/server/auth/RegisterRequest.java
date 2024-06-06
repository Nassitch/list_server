package com.list.server.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    // Log.
    private String pseudo;
    private String email;
    private String password;
    private String requiredRole;

    // User.
    private String firstName;
    private String lastName;
    private String picture;
    private String address;
    private String city;
    private String zipCode;
}
