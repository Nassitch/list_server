package com.list.server.util;

import com.list.server.auth.Login;
import com.list.server.demo.LoginRepository;
import com.list.server.auth.Role;
import com.list.server.domain.entities.User;
import com.list.server.domain.enums.Status;
import com.list.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private Long userId;

    @Override
    public void run(String... args) throws Exception {
        if (this.loginRepository.findByEmail("admin@admin.com").isEmpty()) {
            this.createAdminLog();
            this.createUsersLog();
        }
        if (!this.userRepository.findByFirstName("Jérémy").isPresent()) {
            this.createUserProfile();
        }
    }

    private void createAdminLog() {
        Login admin = Login.builder()
                .pseudo("admin")
                .email("admin@list.com")
                .password(passwordEncoder.encode("administrator"))
                .role("ROLE_" + Role.ADMIN)
                .build();

        this.loginRepository.save(admin);
    }

    private void createUsersLog() {
        Login userOne = Login.builder()
                .pseudo("userOne")
                .email("userOne@list.com")
                .password(passwordEncoder.encode("userOne"))
                .role("ROLE_" + Role.USER)
                .build();

        this.loginRepository.save(userOne);

        userId = userOne.getId();
    }

    public void createUserProfile() {
        User userProfileOne = User.builder()
                .firstName("Jérémy")
                .lastName("Grégoire")
                .createdAt(new Date())
                .picture("https://storage.googleapis.com/quest_editor_uploads/1rtbgvXNRdje4p0Vsj7TJVVtS9lru6AK.jpg")
                .address("171 Rue Lucien Faure")
                .city("Bordeaux")
                .zipCode("33310")
                .status(Status.ACTIVATED)
                .loginId(userId)
                .build();
        this.userRepository.save(userProfileOne);
        System.out.println("Signal : " + userId);
    }
}
