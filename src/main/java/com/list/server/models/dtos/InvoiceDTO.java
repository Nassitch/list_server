package com.list.server.models.dtos;

import com.list.server.domain.entities.Invoice;

import java.util.Date;

public record InvoiceDTO(
        Long id,
        Date createdAt,
        short total
) {
    public static InvoiceDTO mapFromEntity(Invoice invoice) {
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getCreatedAt(),
                invoice.getTotal()
        );
    }
}
