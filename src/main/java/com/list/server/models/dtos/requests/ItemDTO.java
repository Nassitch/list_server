package com.list.server.models.dtos.requests;


import com.list.server.domain.entities.Item;

public record ItemDTO(
        Long id,
        String name,
        Long categoryId
) {
    public static ItemDTO mapFromRequestJson(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getCategory().getId()
        );
    }
}
