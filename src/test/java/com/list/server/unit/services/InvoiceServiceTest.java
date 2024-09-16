package com.list.server.unit.services;

import com.list.server.domain.entities.*;
import com.list.server.models.dtos.InvoiceDTO;
import com.list.server.models.requests.InvoiceRequestDTO;
import com.list.server.repositories.InvoiceRepository;
import com.list.server.repositories.ShopRepository;
import com.list.server.services.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Item item = new Item();
        Shop shop = new Shop();
        Market market = new Market();
        User user = new User();

        shop.setItems(new ArrayList<>(Arrays.asList(item)));
        short total = 70;
        LocalDateTime currentDate = LocalDateTime.now();
        Invoice invoiceOne = new Invoice(1L, currentDate, total, market, shop, user);
        Invoice invoiceTwo = new Invoice(2L, currentDate, total, market, shop, user);

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
            invoiceService.getById(1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("404 NOT_FOUND \"This id: '1' was not found.\"", exception.getMessage());
        verify(invoiceRepository, times(1)).findById(1L);
    }

    @Test
    void testAdd() {
        Invoice invoice = new Invoice();
        Shop shop = new Shop();
        LocalDateTime currentDate = LocalDateTime.now();
        short total = 70;
        InvoiceRequestDTO invoiceRequestDTO = new InvoiceRequestDTO(currentDate, total, 1L, 1L, 1L);

        shop.setCreatedAt(LocalDateTime.now());
        shop.setCompleted(true);
        invoice.setShop(shop);

        when(invoiceRepository.existsByShopId(invoiceRequestDTO.shopId())).thenReturn(false);
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        when(shopRepository.save(shop)).thenReturn(shop);

        InvoiceRequestDTO result = invoiceService.add(invoice, invoiceRequestDTO);

        assertNotNull(result);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void testEdit() {
        Invoice invoiceFromDB = new Invoice();

        Market market = new Market();
        Shop shop = new Shop();
        User user = new User();

        LocalDateTime currentDate = LocalDateTime.now();
        short total = 70;
        invoiceFromDB.setId(1L);
        invoiceFromDB.setShop(shop);
        market.setId(1L);
        shop.setId(1L);
        user.setId(1L);
        invoiceFromDB.setCreatedAt(currentDate);
        Invoice newInvoice = new Invoice(2L, currentDate, total, market, shop, user);

        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoiceFromDB));
        when(invoiceRepository.save(invoiceFromDB)).thenReturn(invoiceFromDB);
        when(shopRepository.save(shop)).thenReturn(shop);

        Invoice result = invoiceService.edit(newInvoice, 1L);

        assertEquals(currentDate, result.getCreatedAt());
        assertEquals(70, result.getTotal());
        assertEquals(1L, result.getMarket().getId());
        assertEquals(1L, result.getShop().getId());
        assertEquals(1L, result.getUser().getId());
        verify(invoiceRepository, times(1)).findById(1L);
        verify(invoiceRepository, times(1)).save(invoiceFromDB);
    }

    @Test
    void testRemove_Success() {
        when(invoiceRepository.existsById(1L)).thenReturn(true);

        String result = invoiceService.remove(1L);

        assertEquals("id: 1", result);
        verify(invoiceRepository, times(1)).existsById(1L);
    }

    @Test
    void testRemove_NotFound() {
        when(invoiceRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invoiceService.remove(1L);
        });

        assertEquals("This id: '1' was not founded.", exception.getMessage());
        verify(invoiceRepository, times(1)).existsById(1L);
    }
}
