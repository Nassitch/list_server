package com.list.server.controllers.protecteds;

import com.list.server.models.dtos.InvoiceDTO;
import com.list.server.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/invoice")
@RequiredArgsConstructor
@Component("protectedInvoiceController")
public class InvoiceController {

    private final InvoiceService service;

    @GetMapping("/read/all")
    public List<InvoiceDTO> readAll() {
        return this.service.getAll();
    }
}
