package com.example.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyectointegrador.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    boolean existsByEmail(String email);
}