package com.list.server.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLogRequest {

    private String pseudo;
    private String email;
    private String password;
    private String requiredRole;
}
