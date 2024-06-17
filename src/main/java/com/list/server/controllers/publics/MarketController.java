package com.list.server.controllers.publics;

import com.list.server.domain.entities.Market;
import com.list.server.services.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/market")
@RequiredArgsConstructor
@Component("publicMarketController")
public class MarketController {

    private final MarketService service;

    @GetMapping("/read/all")
    public List<Market> readAll() {
        return this.service.getAll();
    }

    @GetMapping("/read/{id}")
    public Market readById(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }
}
