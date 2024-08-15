package com.list.server.services;

import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.ShopDTO;
import com.list.server.models.requests.ShopRequestDTO;
import com.list.server.repositories.ShopRepository;
import com.list.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository repository;
    private final UserRepository userRepository;
    private final ItemService itemService;

    public List<ShopDTO> getAll() {
        List<Shop> shops = this.repository.findAll();
        List<ShopDTO> shopDTOS = shops.stream()
                .map(shop -> {
                    int count = countItems(shop);
                    return ShopDTO.mapFromEntity(shop, count);
                }).toList();
        return shopDTOS;
    }

    public Shop getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id: '" + id + "' was not founded."));
    }

    public List<Shop> getAllByUserId(Long id) {
        return this.repository.findByUserId(id);
    }

    public int countItems(Shop shop) {
        int count = shop.getItems().size();
        return count;
    }

    public Shop add(Shop shop) {
        shop.setCreatedAt(LocalDateTime.now());
        return this.repository.save(shop);
    }

    public ShopRequestDTO edit(ShopRequestDTO shopDTO, Long id) {
        Shop shopEdited = getById(id);

        shopEdited.setUser(userRepository.findById(shopDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + shopDTO.userId())));

        List<Item> updatedItems = shopDTO.items().stream()
                .map(itemDTO -> itemService.getById(itemDTO.id()))
                .collect(Collectors.toList());

        shopEdited.setItems(updatedItems);
        shopEdited.setCreatedAt(LocalDateTime.now());
        shopEdited.setCompleted(false);


        repository.save(shopEdited);
        return shopDTO;
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
