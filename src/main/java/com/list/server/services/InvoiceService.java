package com.list.server.services;

import com.list.server.domain.entities.Invoice;
import com.list.server.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository repository;

    public List<Invoice> getAll() {
        return this.repository.findAll();
    }

    public Invoice getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("This id: '" + id + "' was not founded."));
    }

    public Invoice add(Invoice invoice) {
        return this.repository.save(invoice);
    }

    public Invoice edit(Invoice invoice, Long id) {
        Invoice invoiceEdit = getById(id);

        invoiceEdit.setTotal(invoice.getTotal());
        invoiceEdit.setMarket(invoice.getMarket());
        invoiceEdit.setUser(invoice.getUser());

        return this.repository.save(invoiceEdit);
    }

    public String remove(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "id: " + id;
        } else {
            throw new IllegalArgumentException("This id: '" + id + "' was not founded.");
        }
    }
}
