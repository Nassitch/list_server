package com.list.server.models.dtos.responses;

import com.list.server.domain.entities.Invoice;

import java.util.Date;

public record InvoiceDTO(
        Long id,
        Date createdAt,
        short total,
        Long marketId,
        Long userId
) {
    public static InvoiceDTO mapFromEntity(Invoice invoice) {
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getCreatedAt(),
                invoice.getTotal(),
                invoice.getMarket().getId(),
                invoice.getUser().getId()
        );
    }
}
