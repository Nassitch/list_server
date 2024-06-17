package com.list.server.models.dtos;

import com.list.server.domain.entities.Shop;

import java.util.Date;

public record ShopDTO(
        Long id,
        Date createdAt
) {
    public static ShopDTO mapFromEntity(Shop shop) {
        return new ShopDTO(
                shop.getId(),
                shop.getCreatedAt()
        );
    }
}
