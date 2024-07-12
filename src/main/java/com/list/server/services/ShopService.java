package com.list.server.services;

import com.list.server.domain.entities.Category;
import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.ShopDTO;
import com.list.server.repositories.InvoiceRepository;
import com.list.server.repositories.ShopRepository;
import com.list.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository repository;
    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;
    private final ItemService itemService;
    private final CategoryService categoryService;

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
        return this.repository.save(shop);
    }

    public ShopDTO edit(ShopDTO shopDTO, Long id) {
        Shop shopEdited = getById(id);

        shopEdited.setCreatedAt(shopDTO.createdAt());
        shopEdited.setUser(userRepository.findById(shopDTO.userId()).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + shopDTO.userId())));

        List<Item> updatedItems = shopDTO.categories().stream()
                .flatMap(categoryDTO -> categoryDTO.items().stream()
                        .map(itemDTO -> {
                            Item item = itemService.getById(itemDTO.id());
                            Category category = categoryService.getById(categoryDTO.id());
                            item.setCategory(category);
                            return item;
                        })
                ).collect(Collectors.toList());

        shopEdited.setItems(updatedItems);

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
