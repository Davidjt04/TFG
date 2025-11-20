package com.david.tfg.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.FinalCite;
import com.david.tfg.services.FinalCiteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cita")
public class FinalCiteRestController {
 //Inyectamos el servicio
    private final FinalCiteService service;

    public FinalCiteRestController(FinalCiteService service) {
        this.service = service;
    }

    //se muestran todos los articulos
    @GetMapping("/lista")
    public List<FinalCite> lista(){
        //va a sacar una lista de articulos 
        return this.service.findAll();
    }

    @GetMapping("/borrar/{id}")
    public ResponseEntity<FinalCite> borrar(@PathVariable Integer id){
        //va a borrar un Date
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
    public ResponseEntity<FinalCite> editar(@PathVariable Integer id) {
    Optional<FinalCite> FinalCiteOpt = service.findById(id);
        if (FinalCiteOpt.isPresent()) {
            FinalCite FinalCite = FinalCiteOpt.get();
            return ResponseEntity.ok(FinalCite);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/crear")
    public ResponseEntity<FinalCite> crear(){
        //va a crear un Date
        return ResponseEntity.ok(new FinalCite());
    }

    //guardar
    @PostMapping("/guardar")
    public ResponseEntity<FinalCite> guardar(@RequestBody FinalCite FinalCite  ){
        //parte de creacion de un Date 
        if(FinalCite.getIdCita() == 0){
            service.save(FinalCite);
            return ResponseEntity.ok(FinalCite);
        }else{
            //parte de modificacin de un Date 
            Optional<FinalCite> FinalCiteSinActu = service.findById(FinalCite.getIdCita());
            //cogemos el objeto del optional 
            FinalCite FinalCiteActu = FinalCiteSinActu.get();
            FinalCiteActu.setHora(FinalCite.getHora());
            FinalCiteActu.setFecha(FinalCite.getFecha());
            FinalCiteActu.setPrecioCorte(FinalCite.getPrecioCorte());
            FinalCiteActu.setNombreCorte(FinalCite.getNombreCorte());
            FinalCiteActu.setNombreTrabajador(FinalCite.getNombreTrabajador());

            service.save(FinalCiteActu);
            return ResponseEntity.ok(FinalCiteActu);  
        }
            
    }
}
