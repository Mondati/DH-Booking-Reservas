package com.example.proyectointegrador.security;

import com.example.proyectointegrador.entity.Usuario;
import com.example.proyectointegrador.entity.UsuarioRol;
import com.example.proyectointegrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadorDeDatos implements ApplicationRunner {
    private final UsuarioService usuarioService;

    @Autowired
    public CargadorDeDatos(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Crear un objeto para encriptar contraseñas con BCrypt.
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Definir una contraseña simple (para demostración).
        String pass = "p";

        // Encriptar la contraseña utilizando BCrypt.
        String passCifrada = passwordEncoder.encode(pass);

        // Crear y guardar un usuario con el rol "ROL_USER".
        usuarioService.guardarUsuario(new Usuario("Diego", "Rueda", "diego@gmail.com", passCifrada, UsuarioRol.ROL_USER));

        // Crear y guardar un usuario con el rol "ROL_ADMIN".
        usuarioService.guardarUsuario(new Usuario("Agustin", "Rueda", "agustin@gmail.com", passCifrada, UsuarioRol.ROL_ADMIN));
    }

}
