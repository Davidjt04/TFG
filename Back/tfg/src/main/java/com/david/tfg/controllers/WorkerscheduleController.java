package com.david.tfg.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.Workerschedule;
import com.david.tfg.services.WorkerscheduleService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/workerschedule")
public class WorkerscheduleController {
    //Inyectamos el servicio
    private final WorkerscheduleService service;

    public WorkerscheduleController (WorkerscheduleService service) {
        this.service = service;
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


}
