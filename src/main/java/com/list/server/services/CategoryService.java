package com.list.server.services;

import com.list.server.domain.entities.Category;
import com.list.server.domain.entities.Shop;
import com.list.server.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id: '" + id + "' was not founded."));
    }

    public int countItems(Category category) {
        int count = category.getItems().size();
        return count;
    }

    public Category add(Category category) {
        return repository.save(category);
    }

    public Category edit(Category category, Long id) {
        Category categoryFounded = getById(id);

        categoryFounded.setName(category.getName());
        category.setPicture(category.getPicture());
        categoryFounded.setItems(category.getItems());

        return this.repository.save(categoryFounded);
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
