package com.example.proyectointegrador.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE) // Opcional: para cargar los roles de manera inmediata
    private Set<com.example.proyectointegrador.entity.Role> roles;

    // Getters y setters aquí...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<com.example.proyectointegrador.entity.Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<com.example.proyectointegrador.entity.Role> roles) {
        this.roles = roles;
    }
}
