package com.example.proyectointegrador.service;

import com.example.proyectointegrador.dto.ReservaDto;
import com.example.proyectointegrador.entity.Comida;
import com.example.proyectointegrador.entity.Reserva;
import com.example.proyectointegrador.repository.ComidaRepository;
import com.example.proyectointegrador.repository.ReservaRepository;
import com.example.proyectointegrador.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    public void crearReserva(ReservaDto reservaDto) {
        Reserva reserva = new Reserva();

        // Setear atributos de Reserva desde ReservaDto
        reserva.setUser(userRepository.findById(reservaDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado")));

        // Obtener las comidas desde el repositorio
        List<Comida> comidas = comidaRepository.findAllById(reservaDto.getComidaIds());
        reserva.setComidas(comidas);

        // Setear la fecha de reserva
        reserva.setFecha_reserva(reservaDto.getFechaReserva());

        // Guardar la reserva
        reservaRepository.save(reserva);
    }


    public List<Date> obtenerFechasReservasPorComidaId(Integer comidaID) {
        return reservaRepository.findFechasReservasPorComidaId(comidaID);
    }




}
