package com.example.petstoreapp.repository;

import com.example.petstoreapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
