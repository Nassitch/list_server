package com.list.server.controllers.protecteds;

import com.list.server.domain.entities.LogDetail;
import com.list.server.services.LogDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/log-detail")
@RequiredArgsConstructor
@Component("protectedLogDetailController")
public class LogDetailController {

    private final LogDetailService service;

    @GetMapping("/read/all")
    public List<LogDetail> readAll() {
        return this.service.getAll();
    }

    @GetMapping("/read/{id}")
    public LogDetail readById(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String logDetailDeleted = this.service.remove(id);
            return new ResponseEntity<>(logDetailDeleted, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            String errorMsg = "This id: '" + id + "' was not founded.";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
