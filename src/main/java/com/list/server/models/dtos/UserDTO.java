package com.list.server.models.dtos;

import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Shop;
import com.list.server.domain.entities.User;
import com.list.server.domain.enums.Status;

import java.util.Date;
import java.util.List;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        Date createdAt,
        String picture,
        String address,
        String city,
        String zipCode,
        Status status,
        Long loginId
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
                user.getStatus(),
                user.getLogin().getId()
        );
    }
}
