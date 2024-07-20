package com.list.server.controllers.protecteds;

import com.list.server.domain.entities.Item;
import com.list.server.models.dtos.ItemDTO;
import com.list.server.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/item")
@RequiredArgsConstructor
@Component("protectedItemController")
public class ItemController {

    private final ItemService service;

    @PostMapping("/create")
    public Item create(@RequestBody Item item) {
        return this.service.add(item);
    }

    @PutMapping("/update/{id}")
    public Item update(@RequestBody Item item, @PathVariable("id") Long id) {
        return this.service.edit(item, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String itemDeleted = this.service.remove(id);
            return new ResponseEntity<>(itemDeleted, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            String errorMsg = "This id: '" + id + "' was not founded.";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
