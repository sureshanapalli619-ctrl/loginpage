package com.example.reglogBackend.controller;


import com.example.reglogBackend.dto.AuthResponse;
import com.example.reglogBackend.dto.LoginRequest;
import com.example.reglogBackend.dto.SignUpRequest;
import com.example.reglogBackend.entity.User;
import com.example.reglogBackend.service.AuthService;
import com.example.reglogBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService,
                          AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody SignUpRequest request) {

        User user = userService.signup(request);

        return ResponseEntity.ok(
                new AuthResponse(
                        "Signup successful",
                        user.getUsername()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }
}