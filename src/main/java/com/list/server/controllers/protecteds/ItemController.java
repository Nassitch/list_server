package com.list.server.controllers.protecteds;

import com.list.server.domain.entities.Item;
import com.list.server.models.dtos.ItemDTO;
import com.list.server.models.responses.DeleteResponse;
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
    public ResponseEntity<DeleteResponse> delete(@PathVariable("id") Long id) {
        try {
            this.service.remove(id);
            DeleteResponse response = new DeleteResponse(id, "Delete successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            DeleteResponse response = new DeleteResponse(id, "This id not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
