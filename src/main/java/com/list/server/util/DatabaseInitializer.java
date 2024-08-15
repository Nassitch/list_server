package com.list.server.util;

import com.list.server.auth.Login;
import com.list.server.repositories.LoginRepository;
import com.list.server.domain.enums.Role;
import com.list.server.domain.entities.Admin;
import com.list.server.domain.entities.Category;
import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.User;
import com.list.server.domain.enums.Status;
import com.list.server.repositories.AdminRepository;
import com.list.server.repositories.CategoryRepository;
import com.list.server.repositories.ItemRepository;
import com.list.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
//        if (this.loginRepository.findByEmail("admin@admin.com").isEmpty()) {
//            this.createAdminLog();
//        }
//        if (!this.userRepository.findByFirstName("Jérémy").isPresent()) {
//            this.createUsersLog();
//        }
//
//        this.createCategoryAndItems();
    }

    private void createAdminLog() {
        Login admin = new Login();
        admin.setPseudo("admin");
        admin.setEmail("admin@list.com");
        admin.setPassword(passwordEncoder.encode("administrator"));
        admin.setRole("ROLE_" + Role.ADMIN);

        this.loginRepository.save(admin);

        Admin adminInfo = Admin.builder()
                .os("Windows")
                .browser("Firefox")
                .login(admin)
                .build();
        this.adminRepository.save(adminInfo);
    }

    private void createUsersLog() {
        Login userOne = new Login();
        userOne.setPseudo("userOne");
        userOne.setEmail("userOne@list.com");
        userOne.setPseudo(passwordEncoder.encode("userOne"));
        userOne.setRole("ROLE_" + Role.USER);

        this.loginRepository.save(userOne);

        User userProfileOne = User.builder()
                .firstName("Jérémy")
                .lastName("Grégoire")
                .createdAt(LocalDateTime.now())
                .picture("https://storage.googleapis.com/quest_editor_uploads/1rtbgvXNRdje4p0Vsj7TJVVtS9lru6AK.jpg")
                .address("171 Rue Lucien Faure")
                .city("Bordeaux")
                .zipCode("33310")
                .status(Status.ACTIVATED)
                .login(userOne)
                .build();
        this.userRepository.save(userProfileOne);
    }

    private void createCategoryAndItems() {
        Category categoryOne = Category.builder()
                .name("Épicerie salée")
                .createdAt(LocalDateTime.now())
                .build();
        this.categoryRepository.save(categoryOne);

        Item itemOne = Item.builder()
                .name("Pâte")
                .category(categoryOne)
                .build();
        this.itemRepository.save(itemOne);

        Item itemTwo = Item.builder()
                .name("Riz")
                .category(categoryOne)
                .build();
        this.itemRepository.save(itemTwo);

    }
}
