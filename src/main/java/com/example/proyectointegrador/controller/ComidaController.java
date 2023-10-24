package com.example.proyectointegrador.controller;

import com.example.proyectointegrador.entity.Comida;
import com.example.proyectointegrador.entity.ComidaRequest;
import com.example.proyectointegrador.exception.ResoucerNotFoundException;
import com.example.proyectointegrador.service.ComidaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comidas")
public class ComidaController {

    private final ComidaService comidaService;

    @Autowired
    public ComidaController(ComidaService comidaService) {
        this.comidaService = comidaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comida> buscarComidaPorId(@PathVariable("id") Long id) throws ResoucerNotFoundException   {
        Optional<Comida> comidaBuscada = comidaService.buscarComidaPorId(id);
        if (comidaBuscada.isPresent()) {
            return ResponseEntity.ok(comidaBuscada.get());
        } else {
            throw new ResoucerNotFoundException("Comida no encontrada con ID: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Comida>> listarComidas() {
        return ResponseEntity.ok(comidaService.listarTodasLasComidas());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Comida> guardarComida(@RequestBody ComidaRequest comidaRequest) {
        List<String> listaDeUrls = comidaRequest.getImagenes();
        Comida comida = new Comida();
        comida.setNombre(comidaRequest.getNombre());
        comida.setDescripcion(comidaRequest.getDescripcion());
        comida.setUrl(comidaRequest.getUrl());
        comida.setCategoria(comidaRequest.getCategoria());

        Comida comidaGuardada = comidaService.guardarComida(comida, listaDeUrls);

        return ResponseEntity.ok(comidaGuardada);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResoucerNotFoundException {
        Optional<Comida> comidaBuscada = comidaService.buscarComidaPorId(id);
        if (comidaBuscada.isPresent()) {
            comidaService.eliminarComida(id);
            return ResponseEntity.ok("Comida eliminada con éxito: " + id);
        } else {
            throw new ResoucerNotFoundException("No existe el id asociado a una comida en la base de datos " + id);
        }
    }
}
