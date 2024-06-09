package com.list.server.util;

import com.list.server.auth.Login;
import com.list.server.demo.LoginRepository;
import com.list.server.auth.Role;
import com.list.server.domain.entities.Category;
import com.list.server.domain.entities.Item;
import com.list.server.domain.entities.User;
import com.list.server.domain.enums.Status;
import com.list.server.repositories.CategoryRepository;
import com.list.server.repositories.ItemRepository;
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

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    private Long userId;
    private Long categoryId;

    @Override
    public void run(String... args) throws Exception {
        if (this.loginRepository.findByEmail("admin@admin.com").isEmpty()) {
            this.createAdminLog();
            this.createUsersLog();
        }
        if (!this.userRepository.findByFirstName("Jérémy").isPresent()) {
            this.createUserProfile();
        }

        this.createCategoryAndItems();
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

    public void createCategory() {
        Category categoryOne = Category.builder()
                .name("Épicerie salée")
                .createdAt(new Date())
                .build();
        this.categoryRepository.save(categoryOne);

        categoryId = categoryOne.getId();
    }

    public void createItem() {
        Item itemOne = Item.builder()
                .name("Pâte")
                .quantity(1)
//                .categoryId(categoryId)
                .build();
        this.itemRepository.save(itemOne);
        System.out.println("category_id : " + categoryId);
    }

    private void createCategoryAndItems() {
        Category categoryOne = Category.builder()
                .name("Épicerie salée")
                .createdAt(new Date())
                .build();
        this.categoryRepository.save(categoryOne);

        Item itemOne = Item.builder()
                .name("Pâte")
                .quantity(1)
                .category(categoryOne)
                .build();
        this.itemRepository.save(itemOne);

        Item itemTwo = Item.builder()
                .name("Riz")
                .quantity(2)
                .category(categoryOne)
                .build();
        this.itemRepository.save(itemTwo);

        System.out.println("Category ID: " + categoryOne.getId());
        System.out.println("Items saved for Category: " + categoryOne.getName());
    }
}
