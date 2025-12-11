package com.david.tfg.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.User;
import com.david.tfg.entities.WorkerDetail;
import com.david.tfg.entities.Workerschedule;
import com.david.tfg.repos.RepoWorkerschedule;
import com.david.tfg.services.WorkerscheduleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/TRABAJADOR/HORARIO")
public class WorkerscheduleController {

    private final WorkerscheduleService service;
    private final RepoWorkerschedule repo;

    public WorkerscheduleController(WorkerscheduleService service, RepoWorkerschedule repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping("/lista")
    public List<Workerschedule> lista() {
        return this.service.findAll();
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/editar/{id}")
    public ResponseEntity<Workerschedule> editar(@PathVariable Integer id) {
        Optional<Workerschedule> ws = service.findById(id);
        return ws.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Workerschedule> crear() {
        return ResponseEntity.ok(new Workerschedule());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Workerschedule> guardar(@RequestBody Workerschedule workerschedule) {

        if (workerschedule.getId() == 0) {
            service.save(workerschedule);
            return ResponseEntity.ok(workerschedule);
        } else {
            Optional<Workerschedule> ws = service.findById(workerschedule.getId());

            Workerschedule wsActu = ws.get();
            wsActu.setFecha(workerschedule.getFecha());
            wsActu.setHora(workerschedule.getHora());
            wsActu.setDisponible(workerschedule.getDisponible());

            service.save(wsActu);
            return ResponseEntity.ok(wsActu);
        }
    }

    @GetMapping("/horas")
    public List<Workerschedule> getHorasDisponibles(@RequestParam LocalDate fecha) {
        return service.getHorasDisponibles(fecha);
    }

    @GetMapping("/trabajadores")
    public List<Workerschedule> getTrabajadoresDisponibles(
            @RequestParam LocalDate fecha,
            @RequestParam LocalTime hora) {
        return service.getTrabajadoresDisponibles(fecha, hora);
    }

    @GetMapping("/horas-disponibles")
    public List<Workerschedule> getHorasDisponiblesPorTrabajador(
            @RequestParam Integer idTrabajador,
            @RequestParam LocalDate fecha) {

        List<Workerschedule> todos = service.getHorasDisponibles(fecha);

        return todos.stream()
                .filter(ws -> ws.getDetalleTrabajador().getIdDetalle_Trabajador().equals(idTrabajador))
                .toList();
    }

    @PostMapping("/marcar-no-disponible")
    public ResponseEntity<Void> marcarHoraNoDisponible(
            @RequestParam Integer idTrabajador,
            @RequestParam LocalDate fecha,
            @RequestParam LocalTime hora) {

        Optional<Workerschedule> wsOpt =
                repo.findByDetalleTrabajadorIdDetalleTrabajadorAndFechaAndHora(idTrabajador, fecha, hora);

        if (wsOpt.isPresent()) {
            Workerschedule ws = wsOpt.get();
            ws.setDisponible(false);
            repo.save(ws);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/trabajador/marcar-no-disponible")
    public ResponseEntity<Void> marcarHoraNoDisponiblePorTrabajador(
            @RequestParam LocalDate fecha,
            @RequestParam LocalTime hora,
            Authentication authentication) {

        User usuario = (User) authentication.getPrincipal();
        WorkerDetail trabajador = usuario.getWorkerDetail();

        if (trabajador == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Workerschedule> wsOpt =
                repo.findByDetalleTrabajadorIdDetalleTrabajadorAndFechaAndHora(
                        trabajador.getIdDetalle_Trabajador(), fecha, hora);

        if (wsOpt.isPresent()) {
            Workerschedule ws = wsOpt.get();
            ws.setDisponible(false);
            repo.save(ws);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/id")
    public ResponseEntity<Integer> obtenerIdTrabajador(Authentication authentication) {

        User usuario = (User) authentication.getPrincipal();

        if (usuario.getWorkerDetail() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(usuario.getWorkerDetail().getIdDetalle_Trabajador());
    }

  @PostMapping("/marcar-disponible")
public ResponseEntity<?> marcarDisponible(
        @RequestParam Integer idTrabajador,
        @RequestParam String fecha,
        @RequestParam String hora) {

    try {
        LocalDate fechaParseada = LocalDate.parse(fecha);
        LocalTime horaParseada = LocalTime.parse(hora); // ya viene como HH:mm:ss

        service.marcarHoraDisponible(idTrabajador, fechaParseada, horaParseada);

        return ResponseEntity.ok("Horario marcado como disponible");
    } catch (Exception e) {
        System.out.println("[ERROR] No se pudo marcar disponible: " + e.getMessage());
        return ResponseEntity.status(500).body("Error al marcar disponible");
    }
}


}
