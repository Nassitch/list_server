package com.list.server.controllers.protecteds;

import com.list.server.domain.entities.Admin;
import com.list.server.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/admin")
@RequiredArgsConstructor
@Component("protectedAdminController")
public class AdminController {

    private final AdminService service;

    @GetMapping("/read/all")
    public List<Admin> readAll() {
        return this.service.getAll();
    }

    @GetMapping("/read/{id}")
    public Admin readById(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/create")
    public Admin create(@RequestBody Admin admin) {
        return this.service.add(admin);
    }

    @PutMapping("/update/{id}")
    public Admin update(@RequestBody Admin admin, @PathVariable("id") Long id) {
        return this.service.edit(admin, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String adminDeleted = this.service.remove(id);
            return new ResponseEntity<>(adminDeleted, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            String errorMsg = "This id: '" + id + "' was not founded.";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
