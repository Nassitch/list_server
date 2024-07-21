package com.list.server.services;

import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.Shop;
import com.list.server.repositories.InvoiceRepository;
import com.list.server.repositories.ItemRepository;
import com.list.server.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    private final ShopRepository shopRepository;
    private final InvoiceRepository invoiceRepository;

    public List<Item> getAll() {
        return this.repository.findAll();
    }

    public Item getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id: '" + id + "' was not founded."));
    }

    public List<Item> getByCategoryId(Long id) {
        return this.repository.findByCategoryId(id);
    }

    public Item add(Item item) {
        return this.repository.save(item);
    }

    public Item edit(Item item, Long id) {
        Item itemEdited = getById(id);

        itemEdited.setName(item.getName());
        itemEdited.setCategory(item.getCategory());
//        itemEdited.setShops(item.getShops());

        return this.repository.save(itemEdited);
    }

    public void remove(Long id) {
        Item item = getById(id);

        List<Shop> shops = shopRepository.findShopsContainingItem(id);
        for (Shop shop : shops) {
            shop.getItems().remove(item);
            shopRepository.save(shop);
        }

        List<Invoice> invoices = invoiceRepository.findInvoicesContainingItem(id);
        for (Invoice invoice : invoices) {
            Shop shop = invoice.getShop();
            if (shop != null && shop.getItems().contains(item)) {
                shop.getItems().remove(item);
                shopRepository.save(shop);
            }
        }

        repository.delete(item);
    }
}
