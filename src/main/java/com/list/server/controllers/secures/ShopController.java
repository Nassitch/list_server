package com.list.server.controllers.secures;

import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.ShopDTO;
import com.list.server.models.requests.ShopRequestDTO;
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
    private final ShopService shopService;

    @GetMapping("/read/all/{id}")
    public List<ShopDTO> readAllById(@PathVariable("id") Long id) {
        List<Shop> shops = this.service.getAllByUserId(id);
        List<ShopDTO> shopDTOS = shops.stream()
                .map(shop -> {
                    int count = shopService.countItems(shop);
                    return ShopDTO.mapFromEntity(shop, count);
                }).toList();
        return shopDTOS;
    }

    @GetMapping("/read/{id}")
    public ShopDTO readById(@PathVariable("id") Long id) {
        Shop shop = this.service.getById(id);
        int count = shopService.countItems(shop);
        ShopDTO shopDTO = ShopDTO.mapFromEntity(shop, count);
        return shopDTO;
    }

    @PostMapping("/create")
    public Shop create(@RequestBody Shop shop) {
        return this.service.add(shop);
    }

    @PutMapping("/update/{id}")
    public ShopRequestDTO update(@RequestBody ShopRequestDTO shopDTO, @PathVariable("id") Long id) {
        return this.service.edit(shopDTO, id);
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
