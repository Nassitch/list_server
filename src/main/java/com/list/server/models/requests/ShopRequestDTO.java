package com.list.server.models.requests;

import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.ItemDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ShopRequestDTO(
        LocalDateTime createdAt,
        boolean isCompleted,
        List<ItemDTO> items,
        Long userId
) {
    public static ShopRequestDTO mapFromEntity(Shop shop) {
        List<ItemDTO> items = shop.getItems().stream()
                .map(ItemDTO::mapFromEntity)
                .toList();

        return new ShopRequestDTO(
                shop.getCreatedAt(),
                shop.isCompleted(),
                items,
                shop.getUser().getId()
        );
    }
}
