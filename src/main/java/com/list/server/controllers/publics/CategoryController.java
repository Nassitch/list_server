package com.list.server.controllers.publics;

import com.list.server.domain.entities.Category;
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
    public List<Category> readAll() {
        return this.service.getAll();
    }

    @GetMapping("/read/{id}")
    public Category readById(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }
}
