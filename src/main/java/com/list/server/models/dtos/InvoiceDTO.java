package com.list.server.models.dtos;

import com.list.server.domain.entities.Invoice;

import java.time.LocalDateTime;
import java.util.Date;

public record InvoiceDTO(
        Long id,
        LocalDateTime createdAt,
        short total,
        Long marketId,
        Long shopId,
        Long userId
) {
    public static InvoiceDTO mapFromEntity(Invoice invoice) {
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getCreatedAt(),
                invoice.getTotal(),
                invoice.getMarket().getId(),
                invoice.getShop().getId(),
                invoice.getUser().getId()
        );
    }
}
