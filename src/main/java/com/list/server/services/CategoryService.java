package com.list.server.services;

import com.list.server.domain.entities.Category;
import com.list.server.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("This id: '" + id + "' was not founded."));
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public Category edit(Category category, Long id) {
        Category categoryFounded = getById(id);

        categoryFounded.setName(category.getName());
        categoryFounded.setItems(category.getItems());

        return this.categoryRepository.save(categoryFounded);
    }

    public String removeById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "id: " + id;
        } else {
            throw new IllegalArgumentException("This id: '" + id + "' was not founded.");
        }
    }
}
