package com.list.server.models.dtos;

import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.Shop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ShopDTO(
        Long id,
        LocalDateTime createdAt,
        boolean isCompleted,
        int count,
        List<CategoryDTO> categories,
        Long userId
) {
    public static ShopDTO mapFromEntity(Shop shop, int count) {
        List<CategoryDTO> categories = shop.getItems().stream()
                .collect(Collectors.groupingBy(Item::getCategory))
                .entrySet().stream()
                .map(entry -> new CategoryDTO(
                        entry.getKey().getId(),
                        entry.getKey().getName(),
                        entry.getKey().getCreatedAt(),
                        entry.getKey().getPicture(),
                        entry.getValue().stream().map(ItemDTO::mapFromEntity).toList()
                ))
                .toList();

        return new ShopDTO(
                shop.getId(),
                shop.getCreatedAt(),
                shop.isCompleted(),
                count,
                categories,
                shop.getUser().getId()
        );
    }
}
