package com.list.server.services;

import com.list.server.domain.entities.Market;
import com.list.server.repositories.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository repository;

    public List<Market> getAll() {
        return this.repository.findAll();
    }

    public Market getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id: '" + id + "' was not founded."));
    }

    public Market add(Market market) {
        return this.repository.save(market);
    }

    public Market edit(Market market, Long id) {
        Market marketEdit = getById(id);

        marketEdit.setName(market.getName());
        marketEdit.setSize(market.getSize());
        marketEdit.setPlace(market.getPlace());
        marketEdit.setInvoices(market.getInvoices());

        return this.repository.save(marketEdit);
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
