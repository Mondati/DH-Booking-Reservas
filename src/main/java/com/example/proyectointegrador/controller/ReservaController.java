package com.example.proyectointegrador.controller;

import com.example.proyectointegrador.dto.ReservaDto;
import com.example.proyectointegrador.entity.Comida;
import com.example.proyectointegrador.repository.ReservaRepository;
import com.example.proyectointegrador.service.ComidaService;
import com.example.proyectointegrador.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;


    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ComidaService comidaService;

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




    @GetMapping("/{comidaID}")
    public List<List<LocalDate>> obtenerFechasReservasPorComidaId(@PathVariable Integer comidaID) {
        return reservaService.obtenerFechasReservasPorComidaId(comidaID);
    }


    @GetMapping("/buscar")
    public List<Comida> buscarComidas(@RequestParam(required = false) String nombre, @RequestParam(required = false) String categoria, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        List<Object[]> result = reservaRepository.findComidas(nombre, categoria, fechaInicio, fechaFin);

        // Imprimir información de depuración en la consola
        System.out.println("Nombre: " + nombre);
        System.out.println("Categoria: " + categoria);
        System.out.println("FechaInicio: " + fechaInicio);
        System.out.println("FechaFin: " + fechaFin);


        // Procesar manualmente los resultados y convertirlos a instancias de Comida
        List<Comida> comidas = new ArrayList<>();
        for (Object[] row : result) {
            // Suponiendo que el primer elemento del array es el ID de Comida
            Long comidaId = (Long) row[0];
            Comida comida = comidaService.buscarComidaPorId(comidaId).orElse(null);
            if (comida != null) {
                comidas.add(comida);
            }
        }

        return comidas;
    }

}
