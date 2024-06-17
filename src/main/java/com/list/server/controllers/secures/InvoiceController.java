package com.list.server.controllers.secures;

import com.list.server.domain.entities.Invoice;
import com.list.server.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/invoice")
@RequiredArgsConstructor
@Component("secureInvoiceController")
public class InvoiceController {

    private final InvoiceService service;

    @GetMapping("/read/{id}")
    public Invoice readById(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/create")
    public Invoice create(@RequestBody Invoice invoice) {
        return this.service.add(invoice);
    }

    @PutMapping("/update/{id}")
    public Invoice update(@RequestBody Invoice invoice, @PathVariable("id") Long id) {
        return this.service.edit(invoice, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String invoiceDeleted = this.service.remove(id);
            return new ResponseEntity<>(invoiceDeleted, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            String errorMsg = "This id: '" + id + "' was not founded.";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
