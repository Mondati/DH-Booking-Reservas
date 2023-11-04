package com.example.proyectointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyectointegrador.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserNameOrEmail(String username, String email);
    User findByUserName(String userName);
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
}