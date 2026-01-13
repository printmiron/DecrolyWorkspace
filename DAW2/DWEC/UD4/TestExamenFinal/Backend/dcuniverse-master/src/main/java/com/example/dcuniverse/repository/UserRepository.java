package com.example.dcuniverse.repository;

import com.example.dcuniverse.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);
}