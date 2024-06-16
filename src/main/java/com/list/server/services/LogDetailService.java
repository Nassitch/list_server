package com.list.server.services;

import com.list.server.domain.entities.LogDetail;
import com.list.server.repositories.LogDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogDetailService {

    private final LogDetailRepository repository;

    public List<LogDetail> getAll() {
        return this.repository.findAll();
    }

    public LogDetail getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("This id: '" + id + "' was not founded."));
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
