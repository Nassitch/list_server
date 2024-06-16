package com.list.server.services;

import com.list.server.domain.entities.Admin;
import com.list.server.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository repository;

    public List<Admin> getAll() {
        return this.repository.findAll();
    }

    public Admin getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("This id: '" + id + "' was not founded."));
    }

    public Admin add(Admin admin) {
        return this.repository.save(admin);
    }

    public Admin edit(Admin admin, Long id) {
        Admin adminEdited = getById(id);

        adminEdited.setOs(admin.getOs());
        adminEdited.setBrowser(admin.getBrowser());
        adminEdited.setLogin(admin.getLogin());

        return this.repository.save(adminEdited);
    }

    public String remove(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "id: " + id;
        } else {
            throw new IllegalArgumentException("This id: '" + id + "' was not founded.");
        }
    }
}
