package com.example.proyectointegrador.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "comidas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private String url;

    @Column
    private String categoria;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String imagenes; // Serializará la lista de URLs a una cadena JSON
}
