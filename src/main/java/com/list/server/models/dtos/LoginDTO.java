package com.list.server.models.dtos.responses;

import com.list.server.auth.Login;

public record LoginDTO(Long id,
                       String pseudo,
                       String email,
                       String role) {

    public static LoginDTO mapFromEntity(Login login) {
        return new LoginDTO(
                login.getId(),
                login.getPseudo(),
                login.getEmail(),
                login.getRole()
        );
    }
}
