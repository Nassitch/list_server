package com.list.server.unit.services;

import com.list.server.domain.entities.Category;
import com.list.server.domain.entities.Invoice;
import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.Shop;
import com.list.server.models.dtos.CategoryDTO;
import com.list.server.repositories.CategoryRepository;
import com.list.server.repositories.InvoiceRepository;
import com.list.server.repositories.ItemRepository;
import com.list.server.repositories.ShopRepository;
import com.list.server.services.CategoryService;
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

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ShopRepository shopRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Category categoryOne = new Category();
        Category categoryTwo = new Category();
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(categoryOne, categoryTwo));

        List<Category> result = categoryService.getAll();

        assertEquals(2, result.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        Category category = new Category();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryService.getById(1L);

        assertNotNull(result);
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.getById(1L);
        });

        assertEquals("404 NOT_FOUND \"This id: '1' was not founded.\"", exception.getMessage());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testAdd() {
        Category category = new Category();
        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.add(category);

        assertNotNull(result);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testEdit() {
        Category categoryFromDB = new Category();
        Category newCategory = new Category();

        newCategory.setName("category");
        newCategory.setPicture("../images/picture.png");
        LocalDateTime currentDate = LocalDateTime.now();
        categoryFromDB.setCreatedAt(currentDate);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryFromDB));
        when(categoryRepository.save(categoryFromDB)).thenReturn(categoryFromDB);

        CategoryDTO result = categoryService.edit(newCategory, 1L);

        assertEquals("category", result.name());
        assertEquals("../images/picture.png", result.picture());
        assertEquals(currentDate, result.createdAt());
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(categoryFromDB);
    }

    @Test
    void testRemove_Success() {
        Category category = new Category();
        Item item = new Item();
        Shop shop = new Shop();
        shop.setItems(new ArrayList<>(Arrays.asList(item)));
        Invoice invoice = new Invoice();

        category.setItems(new ArrayList<>(Arrays.asList(item)));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(shopRepository.findShopsContainingItem(item.getId())).thenReturn(Arrays.asList(shop));
        when(invoiceRepository.findByShopId(shop.getId())).thenReturn(Arrays.asList(invoice));
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        categoryService.remove(1L);

        verify(shopRepository, times(1)).delete(shop);
        verify(invoiceRepository, times(1)).delete(invoice);
        verify(itemRepository, times(1)).delete(item);
        verify(categoryRepository, times(1)).delete(category);
        assertTrue(category.getItems().isEmpty());
    }

    @Test
    void testRemove_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            categoryService.remove(1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("404 NOT_FOUND \"This id: '1' was not founded.\"", exception.getMessage());

        verify(categoryRepository, times(1)).findById(1L);
    }
}
