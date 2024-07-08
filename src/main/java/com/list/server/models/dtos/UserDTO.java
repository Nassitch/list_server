package com.list.server.models.dtos;

import com.list.server.domain.entities.User;
import com.list.server.domain.enums.Status;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDateTime createdAt,
        String picture,
        String address,
        String city,
        String zipCode,
        Status status,
        Long loginId,
        LocalDateTime lastLog
) {

    public static UserDTO mapFromEntity(User user, LocalDateTime lastLog) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreatedAt(),
                user.getPicture(),
                user.getAddress(),
                user.getCity(),
                user.getZipCode(),
                user.getStatus(),
                user.getLogin().getId(),
                lastLog
        );
    }

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
                user.getStatus(),
                user.getLogin().getId(),
                null
        );
    }
}
