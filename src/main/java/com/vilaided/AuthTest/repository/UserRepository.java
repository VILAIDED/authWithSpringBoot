package com.vilaided.AuthTest.repository;

import com.vilaided.AuthTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserName(String username);
    boolean existsByUserName(String username);
}
