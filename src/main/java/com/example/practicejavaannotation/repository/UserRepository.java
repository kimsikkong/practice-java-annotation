package com.example.practicejavaannotation.repository;

import com.example.practicejavaannotation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
