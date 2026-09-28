package com.example.reglogBackend.repository;


import com.example.reglogBackend.entity.JwtAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtAuthTokenRepository
        extends JpaRepository<JwtAuthToken, Long> {

    Optional<JwtAuthToken> findByToken(String token);

    Optional<JwtAuthToken> findByUserId(Long userId);

    void deleteByToken(String token);

    void deleteByUserId(Long userId);
}