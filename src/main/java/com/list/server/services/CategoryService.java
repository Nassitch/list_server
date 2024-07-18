package com.list.server.services;

import com.list.server.domain.entities.Category;
import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.CategoryDTO;
import com.list.server.repositories.CategoryRepository;
import com.list.server.repositories.InvoiceRepository;
import com.list.server.repositories.ItemRepository;
import com.list.server.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final InvoiceRepository invoiceRepository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id: '" + id + "' was not founded."));
    }

    public Category add(Category category) {
        category.setCreatedAt(LocalDateTime.now());
        return repository.save(category);
    }

    public CategoryDTO edit(Category category, Long id) {
        Category categoryFounded = getById(id);

        categoryFounded.setName(category.getName());
        categoryFounded.getCreatedAt();
        categoryFounded.setPicture(category.getPicture());

        CategoryDTO categoryDTO = CategoryDTO.mapFromEntity(categoryFounded);
        this.repository.save(categoryFounded);

        return categoryDTO;
    }

    public void remove(Long id) {
        Category category = getById(id);

        for (Item item : category.getItems()) {
            List<Shop> shops = shopRepository.findShopsContainingItem(item.getId());
            for (Shop shop : shops) {
                shop.getItems().remove(item);
                List<Invoice> invoices = invoiceRepository.findByShopId(shop.getId());
                for (Invoice invoice : invoices) {
                    invoiceRepository.delete(invoice);
                }
                shopRepository.delete(shop);
            }
            Item attachedItem = itemRepository.findById(item.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
            itemRepository.delete(attachedItem);
        }

        category.getItems().clear();
        repository.save(category);

        Category attachedCategory = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        repository.delete(attachedCategory);
    }
}
