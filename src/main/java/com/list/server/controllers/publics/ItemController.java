package com.list.server.controllers.publics;

import com.list.server.domain.entities.Item;
import com.list.server.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/item")
@RequiredArgsConstructor
@Component("publicItemController")
public class ItemController {

    private final ItemService service;

    @GetMapping("/read/all")
    public List<Item> readAll() {
        return this.service.getAll();
    }

    @GetMapping("/read/{id}")
    public Item readById(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }
}
