package com.list.server.models.requests;

import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Market;
import com.list.server.domain.entities.Shop;
import com.list.server.domain.entities.User;
import com.list.server.models.dtos.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvoiceRequestDTO(
        LocalDateTime createdAt,
        BigDecimal total,
        Long marketId,
        Long shopId,
        Long userId
) {
    public static Invoice mapFromEntity(InvoiceRequestDTO invoiceDTO, Market market, Shop shop, User user) {
        Invoice invoice = new Invoice();
        invoice.setCreatedAt(invoiceDTO.createdAt());
        invoice.setTotal(invoiceDTO.total());
        invoice.setMarket(market);
        invoice.setShop(shop);
        invoice.setUser(user);
        return invoice;
    };
}
