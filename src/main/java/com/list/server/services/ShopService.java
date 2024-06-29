package com.list.server.services;

import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.ShopDTO;
import com.list.server.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository repository;

    public List<ShopDTO> getAll() {
        List<Shop> shops = this.repository.findAll();
        List<ShopDTO> shopDTOS = shops.stream().map(ShopDTO::mapFromEntity).toList();
        return shopDTOS;
    }

    public Shop getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id: '" + id + "' was not founded."));
    }

    public List<Shop> getAllByUserId(Long id) {
        return this.repository.findByUserId(id);
    }

    public Shop add(Shop shop) {
        return this.repository.save(shop);
    }

    public Shop edit(Shop shop, Long id) {
        Shop shopEdited = getById(id);

//        shopEdited.setItem(shop.getItem());
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
