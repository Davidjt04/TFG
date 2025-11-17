package com.david.tfg.repos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.david.tfg.entities.Workerschedule;

public interface RepoWorkerschedule extends JpaRepository<Workerschedule, Integer> {

    List<Workerschedule> findByFechaAndDisponible(LocalDate fecha, boolean disponible);

    List<Workerschedule> findByFechaAndHoraAndDisponible(LocalDate fecha, LocalTime hora, boolean disponible);

    @Query("SELECT w FROM Workerschedule w " +
           "WHERE w.detalleTrabajador.idDetalle_Trabajador = :id " +
           "AND w.fecha = :fecha " +
           "AND w.hora = :hora")
    Optional<Workerschedule> findByDetalleTrabajadorIdDetalleTrabajadorAndFechaAndHora(
                @Param("id") Integer id,
                @Param("fecha") LocalDate fecha,
                @Param("hora") LocalTime hora
        );
}
