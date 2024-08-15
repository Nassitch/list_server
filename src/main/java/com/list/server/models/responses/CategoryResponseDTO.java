package com.list.server.models.responses;

import com.list.server.domain.entities.Category;

import java.time.LocalDateTime;

public record CategoryResponseDTO(
        Long id,
        String name,
        LocalDateTime createdAt,
        String picture
) {
    public static CategoryResponseDTO mapFromEntity(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getPicture()
        );
    }
}
