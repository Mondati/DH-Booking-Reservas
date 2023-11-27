package com.example.proyectointegrador.repository;

import com.example.proyectointegrador.entity.Comida;
import com.example.proyectointegrador.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    Optional<Reserva> findById(Integer id);

    @Query(value ="SELECT fecha_inicio, fecha_fin FROM reservas JOIN reserva_comida WHERE reservas.id = reserva_comida.reserva_id and reserva_comida.comida_id = :comidaID and fecha_inicio >= CURRENT_DATE", nativeQuery = true)
    List<Object[]> findFechasReservasPorComidaId(@Param("comidaID") Integer comidaID);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reserva_comida WHERE comida_id  = :comidaId", nativeQuery = true)
    void eliminarReserva(@Param("comidaId") Long comidaId);


    @Query(value = "SELECT COUNT(*) FROM reservas r JOIN reserva_comida rc ON r.id = rc.reserva_id WHERE rc.comida_id = :comidaID AND (r.fecha_inicio <= :fechaFin AND r.fecha_fin >= :fechaInicio)", nativeQuery = true)
    int countReservasSolapadas(@Param("comidaID") Integer comidaID, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);


    @Query(value = "SELECT c.* FROM comidas c WHERE (:nombre IS NULL OR c.nombre LIKE CONCAT('%', :nombre, '%') OR c.descripcion LIKE CONCAT('%', :nombre, '%')) AND (:categoria IS NULL OR c.categoria = :categoria) AND NOT EXISTS (SELECT 1 FROM reserva_comida rc JOIN reservas r ON rc.reserva_id = r.id WHERE c.id = rc.comida_id AND ((r.fecha_inicio <= :fechaFin AND r.fecha_fin >= :fechaInicio) OR (r.fecha_inicio >= :fechaInicio AND r.fecha_inicio <= :fechaFin) OR (r.fecha_fin >= :fechaInicio AND r.fecha_fin <= :fechaFin)))", nativeQuery = true)
    List<Object[]> findComidas(@Param("nombre") String nombre, @Param("categoria") String categoria, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);


}
