package com.list.server.controllers.secures;

import com.list.server.domain.entities.LogDetail;
import com.list.server.domain.entities.User;
import com.list.server.models.dtos.UserDTO;
import com.list.server.models.responses.DeleteResponse;
import com.list.server.repositories.LogDetailRepository;
import com.list.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
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

    @GetMapping("/read/{id}")
    public UserDTO readById(@PathVariable("id") Long id) {
        UserDTO userDTO = service.getByUserId(id);
        return userDTO;
    }

    @PutMapping("/update/{id}")
    public UserDTO update(@RequestBody User user, @PathVariable("id") Long id) {
        UserDTO userDTO = service.edit(user, id);
        return userDTO;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable("id") Long id) {
        try {
            this.service.remove(id);
            DeleteResponse response = new DeleteResponse(id, "Deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException exception) {
            DeleteResponse response = new DeleteResponse(id, "this id is not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
