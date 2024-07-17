package com.list.server.controllers.protecteds;

import com.list.server.domain.entities.Category;
import com.list.server.models.dtos.CategoryDTO;
import com.list.server.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin/category")
@RequiredArgsConstructor
@Component("protectedCategoryController")
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {
        return this.service.add(category);
    }

    @PutMapping("/update/{id}")
    public CategoryDTO update(@RequestBody Category category, @PathVariable("id") Long id) {
        return this.service.edit(category, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String categoryDeleted = service.remove(id);
            return new ResponseEntity<>(categoryDeleted, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            String errorMsg = "This id: '" + id + "' was not founded.";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
