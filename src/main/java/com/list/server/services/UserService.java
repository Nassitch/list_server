package com.list.server.services;

import com.list.server.domain.entities.User;
import com.list.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public User post(User user) {
//        return userRepository.save(user);
//    }
}
