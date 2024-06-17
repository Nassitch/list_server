package com.list.server.controllers.secures;

import com.list.server.domain.entities.User;
import com.list.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/user")
@RequiredArgsConstructor
@Component("secureUserController")
public class UserController {

    private final UserService service;

    @GetMapping("/read/{id}")
    public User readById(@PathVariable("id") Long id) {
        return this.service.getById(id);
    }

    @PutMapping("/update/{id}")
    public User update(@RequestBody User user ,@PathVariable("id") Long id) {
        return this.service.edit(user, id);
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
