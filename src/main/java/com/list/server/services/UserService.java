package com.list.server.services;

import com.list.server.domain.entities.LogDetail;
import com.list.server.domain.entities.User;
import com.list.server.models.dtos.UserDTO;
import com.list.server.repositories.LogDetailRepository;
import com.list.server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final LogDetailRepository logDetailRepository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public UserDTO getByUserId(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        List<LogDetail> logDetails = logDetailRepository.findAllByLoginIdOrderByLastLogDesc(user.getLogin().getId());
        LocalDateTime lastLog = findLatestLog(logDetails);
        return UserDTO.mapFromEntity(user, lastLog);
    }

    private LocalDateTime findLatestLog(List<LogDetail> logDetails) {
        LocalDateTime latestLog = null;
        for (LogDetail logDetail : logDetails) {
            if (latestLog == null || logDetail.getLastLog().isAfter(latestLog)) {
                latestLog = logDetail.getLastLog();
            }
        }
        return latestLog;
    }

    public User add(User user) {
        return this.repository.save(user);
    }

    public UserDTO edit(User user, Long id) {
        User userEdited = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("This id: '" + id + "' was not founded..."));

        userEdited.setFirstName(user.getFirstName());
        userEdited.setLastName(user.getLastName());
        userEdited.setPicture(user.getPicture());
        userEdited.setAddress(user.getAddress());
        userEdited.setCity(user.getCity());
        userEdited.setZipCode(user.getZipCode());
        userEdited.setStatus(user.getStatus());

        this.repository.save(userEdited);
        UserDTO userDisplay = getByUserId(userEdited.getId());
        return userDisplay;
    }

    @Transactional
    public String remove(Long id) {
        if (repository.existsByLoginId(id)) {
            repository.deleteByLoginId(id);
            return "id: " + id;
        } else {
            throw new IllegalArgumentException("This id: '" + id + "' was not founded.");
        }
    }
}
