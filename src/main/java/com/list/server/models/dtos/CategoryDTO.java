package com.list.server.models.dtos;

import com.list.server.domain.entities.Category;

import java.util.Date;
import java.util.List;

public record CategoryDTO(
        Long id,
        String name,
        Date createdAt,
        List<ItemDTO> items
) {
    public static CategoryDTO mapFromEntity(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getItems().stream().map(ItemDTO::mapFromEntity).toList()
        );
    }
}
