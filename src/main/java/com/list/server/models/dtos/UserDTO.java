package com.list.server.models.dtos;

import com.list.server.domain.entities.User;
import com.list.server.domain.enums.Status;

import java.util.Date;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        Date createdAt,
        String picture,
        String address,
        String city,
        String zipCode,
        Status status
) {

    public static UserDTO mapFromEntity(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreatedAt(),
                user.getPicture(),
                user.getAddress(),
                user.getCity(),
                user.getZipCode(),
                user.getStatus()
        );
    }
}
