package com.list.server.services;

import com.list.server.domain.entities.User;
import com.list.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("This id: '" + id + "' was not founded."));
    }

    public User add(User user) {
        return this.repository.save(user);
    }

    public User edit(User user, Long id) {
        User userEdited = getById(id);

        userEdited.setFirstName(user.getFirstName());
        userEdited.setLastName(user.getLastName());
        userEdited.setPicture(user.getPicture());
        userEdited.setAddress(user.getAddress());
        userEdited.setCity(user.getCity());
        userEdited.setZipCode(user.getZipCode());
        userEdited.setLogin(user.getLogin());
        userEdited.setInvoices(user.getInvoices());

        return this.repository.save(userEdited);
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
