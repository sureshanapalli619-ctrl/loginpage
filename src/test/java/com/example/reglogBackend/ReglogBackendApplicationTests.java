package com.example.reglogBackend;

import com.example.reglogBackend.dto.SignUpRequest;
import com.example.reglogBackend.entity.User;
import com.example.reglogBackend.repository.UserRepository;
import com.example.reglogBackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ReglogBackendApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void createTestProfile() {
        Optional<User> existing = userRepository.findByEmail("testuser@example.com");
        if (existing.isEmpty()) {
            SignUpRequest request = new SignUpRequest();
            request.setUsername("testuser");
            request.setEmail("testuser@example.com");
            request.setPassword("Password@123");
            User saved = userService.signup(request);
            System.out.println("=== TEST_USER_CREATED ===");
            System.out.println("ID: " + saved.getId());
            System.out.println("Username: " + saved.getUsername());
            System.out.println("Email: " + saved.getEmail());
            System.out.println("BCrypt Password: " + saved.getPassword());
            System.out.println("=========================");
        } else {
            System.out.println("=== TEST_USER_EXISTS ===");
            System.out.println("ID: " + existing.get().getId());
            System.out.println("Username: " + existing.get().getUsername());
            System.out.println("Email: " + existing.get().getEmail());
            System.out.println("========================");
        }
    }

}

