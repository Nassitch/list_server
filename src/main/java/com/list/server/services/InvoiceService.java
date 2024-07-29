package com.list.server.services;

import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.InvoiceDTO;
import com.list.server.models.requests.InvoiceRequestDTO;
import com.list.server.repositories.InvoiceRepository;
import com.list.server.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository repository;

    private final ShopRepository shopRepository;

    public List<InvoiceDTO> getAll() {
        List<Invoice> invoices = this.repository.findAll();

        List<InvoiceDTO> invoiceDTOS = invoices.stream()
                .map(invoice -> {
                    int count = countItems(invoice.getShop());
                    return InvoiceDTO.mapFromEntity(invoice, count);
                }).toList();
        return invoiceDTOS;
    }

    public int countItems(Shop shop) {
        int count = shop.getItems().size();
        return count;
    }

    public Invoice getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id: '" + id + "' was not founded."));
    }

    public List<InvoiceDTO> getByUserId(Long id) {
        List<Invoice> invoices = this.repository.findByUserId(id);

        List<InvoiceDTO> invoiceDTOS = invoices.stream()
                .map(invoice -> {
                    int count = countItems(invoice.getShop());
                    return InvoiceDTO.mapFromEntity(invoice, count);
                }).toList();
        return invoiceDTOS;
    }

    public InvoiceRequestDTO add(Invoice invoice, InvoiceRequestDTO invoiceDTO) {

        if (repository.existsByShopId(invoiceDTO.shopId())) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Invoice with this shop_id already exists: " + invoiceDTO.shopId());
        }

        Shop shop = invoice.getShop();
        shop.setCompleted(true);
        this.shopRepository.save(shop);

        invoice.setCreatedAt(LocalDateTime.now());
        Invoice invoiceSaved = this.repository.save(invoice);
        return invoiceDTO;
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
