package com.list.server.models.dtos;

import com.list.server.domain.entities.Invoice;

import java.time.LocalDateTime;
import java.util.Date;

public record InvoiceDTO(
        Long id,
        LocalDateTime createdAt,
        short total,
        String picture,
        int count,
        Long userId
) {
    public static InvoiceDTO mapFromEntity(Invoice invoice, int count) {
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getCreatedAt(),
                invoice.getTotal(),
                invoice.getMarket().getPicture(),
                count,
                invoice.getUser().getId()
        );
    }
}
