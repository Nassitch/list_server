package com.list.server.models.dtos;

import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Market;

import java.util.List;

public record MarketDTO(
        Long id,
        String name,
        String size,
        String place,
        List<Long> invoicesIds
) {
    public static MarketDTO mapFromEntity(Market market) {
        return new MarketDTO(
                market.getId(),
                market.getName(),
                market.getSize(),
                market.getPlace(),
                market.getInvoices().stream().map(Invoice::getId).toList()
        );
    }
}
