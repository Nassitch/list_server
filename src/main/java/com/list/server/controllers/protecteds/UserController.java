package com.list.server.controllers.protecteds;

import com.list.server.domain.entities.User;
import com.list.server.models.dtos.UserDTO;
import com.list.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/user")
@RequiredArgsConstructor
@Component("protectedUserController")
public class UserController {

    private final UserService service;

    @GetMapping("/read/all")
    public List<UserDTO> readAll() {
        List<User> user = this.service.getAll();
        List<UserDTO> userDTO = user.stream().map(UserDTO::mapFromEntity).toList();
        return userDTO;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return this.service.add(user);
    }
}
