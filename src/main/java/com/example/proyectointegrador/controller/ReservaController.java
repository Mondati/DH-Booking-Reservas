package com.example.proyectointegrador.controller;

import com.example.proyectointegrador.dto.ReservaDto;
import com.example.proyectointegrador.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> createReserva(@RequestBody ReservaDto reservaDto) {
        try {
            reservaService.crearReserva(reservaDto);
            return new ResponseEntity<>("Reserva creada con éxito", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la reserva: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
