package com.list.server.models.dtos;

import com.list.server.domain.entities.Shop;

import java.time.LocalDateTime;
import java.util.Date;

public record ShopDTO(
        Long id,
        LocalDateTime createdAt
) {
    public static ShopDTO mapFromEntity(Shop shop) {
        return new ShopDTO(
                shop.getId(),
                shop.getCreatedAt()
        );
    }
}
