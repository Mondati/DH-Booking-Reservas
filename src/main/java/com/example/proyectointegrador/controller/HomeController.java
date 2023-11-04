package com.example.proyectointegrador.controller;

import java.util.Collections;
import java.util.List;

import com.example.proyectointegrador.entity.Comida;
import com.example.proyectointegrador.service.ComidaService;
import com.example.proyectointegrador.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.proyectointegrador.dto.LoginDto;
import com.example.proyectointegrador.dto.SignUpDto;
import com.example.proyectointegrador.entity.Role;
import com.example.proyectointegrador.entity.User;
import com.example.proyectointegrador.repository.RoleRepository;
import com.example.proyectointegrador.repository.UserRepository;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserDetail userDetail;

    @Autowired
    public HomeController(UserDetail userDetail) {
        this.userDetail = userDetail;
    }


    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Logueado con exito!!...", HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        // reviso si el usuario existe en la BD
        if(userRepository.existsByUserName(signUpDto.getUsername())){
            return new ResponseEntity<>("El nombre de usuario ya existe!", HttpStatus.BAD_REQUEST);
        }
        // reviso si el email existe en la BD
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("El email ya existe!", HttpStatus.BAD_REQUEST);
        }
        // si no se cumple lo de arriba, creo al usuario, por defecto con rol USER osea usuario normal
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUserName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>("Usuario registrado con exito!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios() {
        return ResponseEntity.ok(userDetail.listarUsuarios());
    }


}