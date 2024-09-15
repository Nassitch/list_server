package com.list.server.unit.services;

import com.list.server.auth.Login;
import com.list.server.domain.entities.Admin;
import com.list.server.repositories.AdminRepository;
import com.list.server.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceTest.class);
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Admin adminOne = new Admin();
        Admin adminTwo = new Admin();
        when(adminRepository.findAll()).thenReturn(Arrays.asList(adminOne, adminTwo));

        List<Admin> result = adminService.getAll();

        assertEquals(2, result.size());
        verify(adminRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        Admin admin = new Admin();
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        Admin result = adminService.getById(1L);

        assertNotNull(result);
        verify(adminRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            adminService.getById(1L);
        });

        assertEquals("This id: '1' was not founded.", exception.getMessage());
        verify(adminRepository, times(1)).findById(1L);
    }

    @Test
    void testAdd() {
        Admin admin = new Admin();
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin result = adminService.add(admin);

        assertNotNull(result);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    void testEdit() {
        Admin adminFromDB = new Admin();
        Admin newAdmin = new Admin();
        Login login = new Login();

        login.setId(1L);
        login.setPseudo("admin");
        login.setEmail("admin@list.com");
        login.setPassword("admin123");
        login.setRole("ROLE_ADMIN");
        newAdmin.setOs("Windows");
        newAdmin.setBrowser("Chrome");
        newAdmin.setLogin(login);

        when(adminRepository.findById(1L)).thenReturn(Optional.of(adminFromDB));
        when(adminRepository.save(adminFromDB)).thenReturn(adminFromDB);

        Admin result = adminService.edit(newAdmin, 1L);

        assertEquals("Windows", result.getOs());
        assertEquals("Chrome", result.getBrowser());
        assertEquals(login, result.getLogin());
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).save(adminFromDB);
    }

    @Test
    void testRemove_Success() {
        when(adminRepository.existsById(1L)).thenReturn(true);

        String result = adminService.remove(1L);

        assertEquals("id: 1", result);
        verify(adminRepository, times(1)).existsById(1L);
        verify(adminRepository, times(1)).existsById(1L);
    }

    @Test
    void testRemove_NotFound() {
        when(adminRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            adminService.remove(1L);
        });

        assertEquals("This id: '1' was not founded.", exception.getMessage());
        verify(adminRepository, times(1)).existsById(1L);
    }
}
