package com.list.server.util;

import com.list.server.auth.Login;
import com.list.server.demo.LoginRepository;
import com.list.server.auth.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (this.loginRepository.findByEmail("admin@admin.com").isEmpty()) {
            this.createAdmin();
            this.createUsers();
        }
    }

    private void createAdmin() {
        Login admin = Login.builder()
                .pseudo("admin")
                .email("admin@list.com")
                .password(passwordEncoder.encode("administrator"))
                .role("ROLE_" + Role.ADMIN)
                .build();

        this.loginRepository.save(admin);
    }

    private void createUsers() {
        Login userOne = Login.builder()
                .pseudo("userOne")
                .email("userOne@list.com")
                .password(passwordEncoder.encode("userOne"))
                .role("ROLE_" + Role.USER)
                .build();

        this.loginRepository.save(userOne);
    }
}
