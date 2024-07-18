package com.list.server.controllers.protecteds;

import com.list.server.domain.entities.Category;
import com.list.server.models.dtos.CategoryDTO;
import com.list.server.models.responses.DeleteResponse;
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
    public ResponseEntity<DeleteResponse> delete(@PathVariable("id") Long id) {
        try {
            service.remove(id);
            DeleteResponse response = new DeleteResponse(id, "Delete successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            DeleteResponse response = new DeleteResponse(id, "This id not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
