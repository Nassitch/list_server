package com.list.server.models.dtos.responses;

import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.Shop;

import java.time.LocalDateTime;
import java.util.List;

public record ShopDTO(
        Long id,
        LocalDateTime createdAt,
        List<Long> itemsIds,
        Long userId
) {
    public static ShopDTO mapFromEntity(Shop shop) {
        return new ShopDTO(
                shop.getId(),
                shop.getCreatedAt(),
                shop.getItems().stream().map(Item::getId).toList(),
                shop.getUser().getId()
        );
    }
}
