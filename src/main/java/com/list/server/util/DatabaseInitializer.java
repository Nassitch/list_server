package com.list.server.util;

import com.list.server.auth.Role;
import com.list.server.domain.entities.User;
import com.list.server.repositories.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(this.userAppRepository.findByEmail("admin@admin.com").isEmpty()) {
            this.createAdmin();
            this.createUsers();
        }
    }

    private void createAdmin() {
        User admin = User.builder()
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("admin"))
                .role("ROLE_" + Role.ADMIN)
                .build();

        this.userAppRepository.save(admin);
    }

    private void createUsers() {
        User user1 = User.builder()
                .firstname("user1")
                .lastname("user1")
                .email("user1@user1.com")
                .password(passwordEncoder.encode("user1"))
                .role("ROLE_" + Role.USER)
                .build();

        this.userAppRepository.save(user1);
    }
}
