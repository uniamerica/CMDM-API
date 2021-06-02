package com.example.cmdmapi.repository;

import com.example.cmdmapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
