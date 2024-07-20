package com.list.server.controllers.protecteds;

import com.list.server.models.dtos.ShopDTO;
import com.list.server.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/shop")
@RequiredArgsConstructor
@Component("protectedShopController")
public class ShopController {

    private final ShopService service;

    @GetMapping("/read/all")
    public List<ShopDTO> readAll() {
        return this.service.getAll();
    }
}
