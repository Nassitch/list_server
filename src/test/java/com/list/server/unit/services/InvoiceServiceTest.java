package com.list.server.unit.services;

import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.InvoiceDTO;
import com.list.server.models.requests.InvoiceRequestDTO;
import com.list.server.repositories.InvoiceRepository;
import com.list.server.services.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Invoice invoiceOne = new Invoice();
        Invoice invoiceTwo = new Invoice();
        when(invoiceRepository.findAll()).thenReturn(Arrays.asList(invoiceOne, invoiceTwo));

        List<InvoiceDTO> result = invoiceService.getAll();

        assertEquals(2, result.size());
        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));

        Invoice result = invoiceService.getById(1L);

        assertNotNull(result);
        verify(invoiceRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(invoiceRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            invoiceService.remove(1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("404 NOT_FOUND \"This id: '1' was not founded.\"", exception.getMessage());
        verify(invoiceRepository, times(1)).findById(1L);
    }

    @Test
    void testAdd() {
        Invoice invoice = new Invoice();
        Shop shop = new Shop();

        shop.setCreatedAt(LocalDateTime.now());
        shop.setCompleted(true);
        invoice.setShop(shop);

        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        InvoiceRequestDTO result = invoiceService.add(invoice);
    }
}
