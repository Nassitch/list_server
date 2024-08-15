package com.list.server.controllers.publics;

import com.list.server.domain.entities.Category;
import com.list.server.models.dtos.CategoryDTO;
import com.list.server.models.responses.CategoryResponseDTO;
import com.list.server.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/category")
@RequiredArgsConstructor
@Component("publicCategoryController")
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/read/all")
    public List<CategoryResponseDTO> readAll() {
        List<Category> categories = this.service.getAll();
        List<CategoryResponseDTO> categoryDTOS = categories.stream().map(CategoryResponseDTO::mapFromEntity).toList();
        return categoryDTOS;
    }

    @GetMapping("/read/{id}")
    public CategoryDTO readById(@PathVariable("id") Long id) {
        Category category = this.service.getById(id);
        CategoryDTO categoryDTO = CategoryDTO.mapFromEntity(category);
        return categoryDTO;
    }
}
