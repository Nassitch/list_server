package com.list.server.controllers.secures;

import com.list.server.domain.entities.LogDetail;
import com.list.server.domain.entities.User;
import com.list.server.models.dtos.UserDTO;
import com.list.server.repositories.LogDetailRepository;
import com.list.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/this/user")
@RequiredArgsConstructor
@Component("secureUserController")
public class UserController {

    private final UserService service;
    private final LogDetailRepository logDetailRepository;

    @GetMapping("/read/{id}")
    public UserDTO readById(@PathVariable("id") Long id) {
        User user = this.service.getById(id);

        LogDetail lastLog = logDetailRepository.findFirstByLoginIdOrderByLastLogDesc(user.getLogin().getId())
                .orElseThrow(() -> new RuntimeException("Log details for login id: '" + user.getLogin().getId() + "' were not found..."));

        return UserDTO.mapFromEntity(user, lastLog.getLastLog());
    }

    @PutMapping("/update/{id}")
    public UserDTO update(@RequestBody User user, @PathVariable("id") Long id) {
        User userEditable = this.service.edit(user, id);
        UserDTO userShowing = this.readById(id);
        return userShowing;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String userDeleted = this.service.remove(id);
            return new ResponseEntity<>(userDeleted, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            String errorMsg = "This id: '" + id + "' was not founded.";
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
        }
    }
}
