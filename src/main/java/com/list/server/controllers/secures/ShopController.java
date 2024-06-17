package com.list.server.controllers.secures;

import com.list.server.domain.entities.Shop;
import com.list.server.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/shop")
@RequiredArgsConstructor
@Component("secureShopController")
public class ShopController {

    private final ShopService service;

    @GetMapping("/read/all")
    public List<Shop> readAll() {
        return this.service.getAll();
    }

    @GetMapping("/read/{id}")
    public Shop readById(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/create")
    public Shop create(@RequestBody Shop shop) {
        return this.service.add(shop);
    }

    @PutMapping("/update/{id}")
    public Shop update(@RequestBody Shop shop, @PathVariable("id") Long id) {
        return this.service.edit(shop, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String shopDeleted = this.service.remove(id);
            return  new ResponseEntity<>(shopDeleted, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            String errorMsg = "This id: '" + id + "' was not founded.";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
