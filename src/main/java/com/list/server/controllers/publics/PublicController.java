package com.list.server.controllers.publics;

import com.list.server.domain.entities.Category;
import com.list.server.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicController {

    private final CategoryService categoryService;

    @GetMapping("/category/all")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getById(id);
    }
}
