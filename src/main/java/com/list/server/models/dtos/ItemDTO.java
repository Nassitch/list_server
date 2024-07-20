package com.list.server.models.dtos;

import com.list.server.domain.entities.Item;

public record ItemDTO(
        Long id,
        String name,
        Long categoryId
) {
    public static ItemDTO mapFromEntity(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getCategory().getId()
        );
    }
}
