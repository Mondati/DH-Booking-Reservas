package com.example.proyectointegrador.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.proyectointegrador.entity.Comida;
import com.example.proyectointegrador.repository.ComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.proyectointegrador.entity.User;
import com.example.proyectointegrador.repository.UserRepository;

@Service
public class UserDetail implements UserDetailsService {
    @Autowired
    UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        User user = userRepo.findByUserNameOrEmail(username, username);
        if(user==null){
            throw new UsernameNotFoundException("No existe cuenta con ese nombre de usuario");
        }

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }


    public List<User> listarUsuarios() {
        return userRepo.findAll();
    }


}