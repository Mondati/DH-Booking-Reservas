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
    public Optional<Favorito> getFavoritoById(@PathVariable Long favoritoId) {
        return favoritoService.oobtenerFavoritoPorId(favoritoId);
    }

    @GetMapping
    public List<Favorito> getAllFavoritos() {
        return favoritoService.obtenerTodosLosFavoritos();
    }

    @PostMapping
    public Favorito addFavorito(@RequestBody Favorito favorito) {
        return favoritoService.guardarFavorito(favorito);
    }

    @DeleteMapping("/{favoritoId}")
    public void deleteFavorito(@PathVariable Long favoritoId) {
        favoritoService.eliminarFavorito(favoritoId);
    }
}

