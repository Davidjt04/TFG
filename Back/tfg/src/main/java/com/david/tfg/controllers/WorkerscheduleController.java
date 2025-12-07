package com.david.tfg.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/workerschedule")
public class WorkerscheduleController {
    //Inyectamos el servicio
    private final WorkerscheduleService service;
    private final RepoWorkerschedule repo;

    public WorkerscheduleController (WorkerscheduleService service, RepoWorkerschedule repo) {
        this.service = service;
        this.repo = repo;
    }

    //se muestran todos las reseñas
    @GetMapping("/lista")
    public List<Workerschedule> lista(){
        //va a sacar una lista de reviews 
        return this.service.findAll();
    }

    @GetMapping("/borrar/{id}")
    public ResponseEntity<Workerschedule> borrar(@PathVariable Integer id){
        //va a borrar una Review
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/editar/{id}")
    public ResponseEntity<Workerschedule> editar(@PathVariable Integer id) {
    Optional<Workerschedule> Workerschedule = service.findById(id);
        if (Workerschedule.isPresent()) {
            Workerschedule review = Workerschedule.get();
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/crear")
    public ResponseEntity<Workerschedule> crear(){
        //va a crear un producto
        return ResponseEntity.ok(new Workerschedule());
    }

    //guardar
    @PostMapping("/guardar")
    public ResponseEntity<Workerschedule> guardar(@RequestBody Workerschedule workerschedule){
        //parte de creacion de una review 
        if(workerschedule.getId() == 0){
            service.save(workerschedule);
            return ResponseEntity.ok(workerschedule);
        }else{
            //parte de modificacin de una review 
            Optional<Workerschedule> Workerschedule = service.findById(workerschedule.getId());
            //cogemos el objeto del optional 
            Workerschedule WorkerscheduleActu = Workerschedule.get();
            WorkerscheduleActu.setFecha(workerschedule.getFecha());
            WorkerscheduleActu.setHora(workerschedule.getHora());
            WorkerscheduleActu.setDisponible(workerschedule.getDisponible());

            service.save(WorkerscheduleActu);
            return ResponseEntity.ok(WorkerscheduleActu);  
        }
            
    }
    //Obtener horas disponibles por fecha
    @GetMapping("/horas")
    public List<Workerschedule> getHorasDisponibles(@RequestParam LocalDate fecha) {
    return service.getHorasDisponibles(fecha);
}

    @GetMapping("/trabajadores")
    public List<Workerschedule> getTrabajadoresDisponibles(
        @RequestParam LocalDate fecha,
        @RequestParam LocalTime hora
    ) {
        return service.getTrabajadoresDisponibles(fecha, hora);
    }

// @GetMapping("/horas-disponibles")
// public List<Workerschedule> getHorasDisponiblesPorTrabajador(
//         @RequestParam Integer idTrabajador,
//         @RequestParam LocalDate fecha) {

//     System.out.println("Backend: recibiendo idTrabajador=" + idTrabajador + ", fecha=" + fecha);

//     List<Workerschedule> todosLosHorarios = service.getHorasDisponibles(fecha);

//     List<Workerschedule> filtrados = todosLosHorarios.stream()
//             .filter(ws -> ws.getDetalleTrabajador().getIdDetalle_Trabajador().equals(idTrabajador))
//             .toList();

//     System.out.println("Backend: horarios filtrados encontrados=" + filtrados.size());

//     return filtrados;
// }

@GetMapping("/horas-disponibles")
public List<Workerschedule> getHorasDisponiblesPorTrabajador(
        @RequestParam Integer idTrabajador,
        @RequestParam LocalDate fecha) {

    // Filtramos usando tu servicio existente
    List<Workerschedule> todosLosHorarios = service.getHorasDisponibles(fecha);
    
    // Solo los del trabajador específico
    return todosLosHorarios.stream()
            .filter(ws -> ws.getDetalleTrabajador().getIdDetalle_Trabajador().equals(idTrabajador))
            .toList();
}

// @PostMapping("/marcar-no-disponible")
// public ResponseEntity<Void> marcarHoraNoDisponible(
//         @RequestParam Integer idTrabajador,
//         @RequestParam LocalDate fecha,
//         @RequestParam LocalTime hora) {

//     Optional<Workerschedule> wsOpt = repo.findByDetalleTrabajadorIdDetalleTrabajadorAndFechaAndHora(
//             idTrabajador, fecha, hora
//     );

//     if(wsOpt.isPresent()) {
//         Workerschedule ws = wsOpt.get();
//         ws.setDisponible(false);
//         repo.save(ws);
//         return ResponseEntity.ok().build();
//     }

//     return ResponseEntity.notFound().build();
// }

@PostMapping("/marcar-no-disponible")
public ResponseEntity<Void> marcarHoraNoDisponible(
        @RequestParam Integer idTrabajador,
        @RequestParam LocalDate fecha,
        @RequestParam LocalTime hora) {

    Optional<Workerschedule> wsOpt = repo.findByDetalleTrabajadorIdDetalleTrabajadorAndFechaAndHora(
            idTrabajador, fecha, hora
    );

    if(wsOpt.isPresent()) {
        Workerschedule ws = wsOpt.get();
        ws.setDisponible(false);
        repo.save(ws);
        return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();
}

// @PostMapping("/TRABAJADOR/marcar-no-disponible")
// public ResponseEntity<Void> marcarHoraNoDisponibleTrabajador(
//         @RequestParam LocalDate fecha,
//         @RequestParam LocalTime hora,
//         Authentication authentication) {

//     User usuario = (User) authentication.getPrincipal();
//     WorkerDetail trabajador = usuario.getWorkerDetail();

//     Optional<Workerschedule> wsOpt =
//         repo.findByDetalleTrabajadorIdDetalleTrabajadorAndFechaAndHora(
//             trabajador.getIdDetalle_Trabajador(), fecha, hora
//         );

//     if (wsOpt.isPresent()) {
//         Workerschedule ws = wsOpt.get();
//         ws.setDisponible(false);
//         repo.save(ws);
//         return ResponseEntity.ok().build();
//     }

//     return ResponseEntity.notFound().build();
// }


@PostMapping("/trabajador/marcar-no-disponible")
public ResponseEntity<Void> marcarHoraNoDisponiblePorTrabajador(
        @RequestParam LocalDate fecha,
        @RequestParam LocalTime hora,
        Authentication authentication) {

    // Obtenemos el usuario logueado
    User usuario = (User) authentication.getPrincipal();

    // Obtenemos el WorkerDetail asociado
    WorkerDetail trabajador = usuario.getWorkerDetail();

    if (trabajador == null) {
        return ResponseEntity.badRequest().build(); // Por si no tiene WorkerDetail
    }

    // Buscamos la hora correspondiente
    Optional<Workerschedule> wsOpt =
        repo.findByDetalleTrabajadorIdDetalleTrabajadorAndFechaAndHora(
            trabajador.getIdDetalle_Trabajador(), fecha, hora
        );

    if (wsOpt.isPresent()) {
        Workerschedule ws = wsOpt.get();
        ws.setDisponible(false);
        repo.save(ws);
        return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();
}


}
