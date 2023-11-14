package com.example.proyectointegrador.service;

import com.example.proyectointegrador.entity.Favorito;
import com.example.proyectointegrador.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;

    @Autowired
    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    public Optional<Favorito> obtenerFavoritoPorId(Long favoritoId) {
        return favoritoRepository.findById(favoritoId);
    }
    public List<Favorito> obtenerTodosLosFavoritos() {
        return favoritoRepository.findAll();
    }

    public Favorito guardarFavorito(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    public void eliminarFavorito(Long favoritoId) {
        favoritoRepository.deleteById(favoritoId);
    }
}
