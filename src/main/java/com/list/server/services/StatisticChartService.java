package com.list.server.services;

import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Shop;
import com.list.server.models.responses.StatisticResponse;
import com.list.server.repositories.InvoiceRepository;
import com.list.server.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticChartService {

    private final ShopRepository shopRepository;
    private final InvoiceRepository invoiceRepository;

    public StatisticResponse getStatistics(Long id, int year) {
        List<Shop> shops = countByYear(id, year, shopRepository::findByUserId, Shop::getCreatedAt);
        List<Invoice> invoices = countByYear(id, year, invoiceRepository::findByUserId, Invoice::getCreatedAt);

        Map<Month, Long> shopCounted = countByMonth(shops);
        Map<Month, Long> totalInvoiceCounted = totalInvoiceByMonth(invoices);

        String[] months = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        Long[] shopCounts = new Long[12];
        Long[] invoiceCounts = new Long[12];

        for (int i = 0; i < 12; i += 1) {
            Month month = Month.of(i + 1);
            shopCounts[i] = shopCounted.getOrDefault(month, 0L);
            invoiceCounts[i] = totalInvoiceCounted.getOrDefault(month, 0L);
        }

        StatisticResponse result = new StatisticResponse(months, shopCounts, invoiceCounts);
        return result;
    }

    private <T> List<T> countByYear(Long id, int year, Function<Long, List<T>> findByUserId, Function<T, LocalDateTime> getCreatedAt) {
        return findByUserId.apply(id).stream()
                .filter(entity -> getCreatedAt.apply(entity).getYear() == year)
                .collect(Collectors.toList());
    }

    private Map<Month, Long> countByMonth(List<Shop> entites) {
        return entites.stream()
                .collect(Collectors.groupingBy(entity -> entity.getCreatedAt().getMonth(), Collectors.counting()));
    }

    private Map<Month, Long> totalInvoiceByMonth(List<Invoice> invoices) {
        return invoices.stream()
                .collect(Collectors.groupingBy(
                        invoice -> invoice.getCreatedAt().getMonth(),
                        Collectors.summingLong(invoice -> invoice.getTotal().longValue())
                ));
    }
}
