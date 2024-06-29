package com.list.server.services;

import com.list.server.domain.entities.Item;
import com.list.server.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public List<Item> getAll() {
        return this.repository.findAll();
    }

    public Item getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id: '" + id + "' was not founded."));
    }

    public Item add(Item item) {
        return this.repository.save(item);
    }

    public Item edit(Item item, Long id) {
        Item itemEdited = getById(id);

        itemEdited.setName(item.getName());
        itemEdited.setQuantity(item.getQuantity());
        itemEdited.setCategory(item.getCategory());
//        itemEdited.setShops(item.getShops());

        return this.repository.save(itemEdited);
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
