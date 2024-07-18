package com.list.server.controllers.protecteds;

import com.list.server.domain.entities.Market;
import com.list.server.models.responses.DeleteResponse;
import com.list.server.services.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/market")
@RequiredArgsConstructor
@Component("protectedMarketController")
public class MarketController {

    private final MarketService service;

    @PostMapping("/create")
    public Market create(@RequestBody Market market) {
        return this.service.add(market);
    }

    @PutMapping("/update/{id}")
    public Market update(@RequestBody Market market, @PathVariable("id") Long id) {
        return this.service.edit(market, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable("id") Long id) {
        try {
            this.service.remove(id);
            DeleteResponse response = new DeleteResponse(id, "delete succesfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            DeleteResponse response = new DeleteResponse(id, "This id is not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
