package com.example.proyectointegrador.repository;

import com.example.proyectointegrador.entity.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Long> {
    boolean existsByNombre(String nombre);
}
