package com.david.tfg.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.Workerschedule;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoWorkerschedule;


@Service
public class WorkerscheduleService implements Crud <Workerschedule,Integer>{
    private final RepoWorkerschedule repo;

    public WorkerscheduleService(RepoWorkerschedule repo) {
        this.repo = repo;
    }

    @Override
    public void save(Workerschedule entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Workerschedule> findById(Integer id) {
        return this.repo.findById(id);    
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Workerschedule> findAll() {
        return this.repo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        this.repo.deleteById(id);

    }

    @Override
    public void deleteAll() {
        this.repo.deleteAll();

    }

    // public void bloquearHoras(Long detalleTrabajadorId, LocalDate fecha, List<LocalTime> horas, boolean bloquearDia) {
    //     if (bloquearDia) {
    //         repo.findByFechaAndDisponible(fecha, true)
    //             .stream()
    //             .filter(h -> h.getDetalleTrabajador().getIdDetalle_Trabajador() == detalleTrabajadorId)
    //             .forEach(h -> h.setDisponible(false));
    //     } else {
    //         for (LocalTime h : horas) {
    //             repo.findByDetalleTrabajadorIdAndFechaAndHora(detalleTrabajadorId, fecha, h)
    //                 .ifPresent(slot -> slot.setDisponible(false));
    //         }
    //     }
    // }

    // @Transactional
    // public void desbloquearHoras(Long detalleTrabajadorId, LocalDate fecha, List<LocalTime> horas, boolean desbloquearDia) {
    //     if (desbloquearDia) {
    //         repo.findByFechaAndDisponible(fecha, false)
    //             .stream()
    //             .filter(h -> h.getDetalleTrabajador().getIdDetalle_Trabajador() == detalleTrabajadorId)
    //             .forEach(h -> h.setDisponible(true));
    //     } else {
    //         for (LocalTime h : horas) {
    //             repo.findByDetalleTrabajadorIdAndFechaAndHora(detalleTrabajadorId, fecha, h)
    //                 .ifPresent(slot -> slot.setDisponible(true));
    //         }
    //     }
    // }

//     public List<Workerschedule> getHorasDisponibles(LocalDate fecha) {
//     return repo.findByFechaAndDisponible(fecha, true);
// }

// Horas disponibles para una fecha
public List<Workerschedule> getHorasDisponibles(LocalDate fecha) {
    return repo.findByFechaAndDisponible(fecha,true);
}

    public List<Workerschedule> getTrabajadoresDisponibles(LocalDate fecha, LocalTime hora) {
        return repo.findByFechaAndHoraAndDisponible(fecha, hora, true);
    }


    // Marcar horario como no disponible cuando se reserve
public void marcarHoraNoDisponible(Integer idTrabajador, LocalDate fecha, LocalTime hora) {
    Optional<Workerschedule> ws = repo.findByDetalleTrabajadorIdDetalleTrabajadorAndFechaAndHora(idTrabajador, fecha, hora);
    ws.ifPresent(slot -> {
        slot.setDisponible(false); // aquí cambia de 1 a 0
        repo.save(slot);
    });
}
}


