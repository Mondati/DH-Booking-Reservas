package com.example.proyectointegrador.controller;

import com.example.proyectointegrador.entity.Favorito;
import com.example.proyectointegrador.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {
    private final FavoritoService favoritoService;

    @Autowired
    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @GetMapping("/{favoritoId}")
    public Optional<Favorito> buscarFavPorId(@PathVariable Long favoritoId) {
        return favoritoService.obtenerFavoritoPorId(favoritoId);
    }

    @GetMapping
    public List<Favorito> buscarTodosLosFavoritos() {
        return favoritoService.obtenerTodosLosFavoritos();
    }

    @PostMapping
    public Favorito guardarFavorito(@RequestBody Favorito favorito) {
        return favoritoService.guardarFavorito(favorito);
    }

    @DeleteMapping("/{favoritoId}")
    public void eliminarFavorito(@PathVariable Long favoritoId) {
        favoritoService.eliminarFavorito(favoritoId);
    }
}

