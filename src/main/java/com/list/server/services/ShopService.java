package com.list.server.services;

import com.list.server.domain.entities.Shop;
import com.list.server.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository repository;

    public List<Shop> getAll() {
        return this.repository.findAll();
    }

    public Shop getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("This id: '" + id + "' was not founded."));
    }

    public List<Shop> getByUserId(Long id) {
        return this.repository.findByUserId(id);
    }

    public Shop add(Shop shop) {
        return this.repository.save(shop);
    }

    public Shop edit(Shop shop, Long id) {
        Shop shopEdited = getById(id);

        shopEdited.setItem(shop.getItem());
        shopEdited.setUser(shop.getUser());

        return this.repository.save(shopEdited);
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
