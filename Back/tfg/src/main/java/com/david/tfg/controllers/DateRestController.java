package com.david.tfg.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.Date;
import com.david.tfg.services.DateService;

@CrossOrigin(origins = "*")
@RestController
public class DateRestController {
 //Inyectamos el servicio
    private final DateService service;

    public DateRestController(DateService service) {
        this.service = service;
    }

    //se muestran todos los articulos
    @GetMapping("/cita/lista")
    public List<Date> lista(){
        //va a sacar una lista de articulos 
        return this.service.findAll();
    }

    @GetMapping("/cita/borrar/{id}")
    public ResponseEntity<Date> borrar(@PathVariable Integer id){
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
    @GetMapping("/cita/editar/{id}")
    public ResponseEntity<Date> editar(@PathVariable Integer id) {
    Optional<Date> DateOpt = service.findById(id);
        if (DateOpt.isPresent()) {
            Date Date = DateOpt.get();
            return ResponseEntity.ok(Date);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/cita/crear")
    public ResponseEntity<Date> crear(){
        //va a crear un Date
        return ResponseEntity.ok(new Date());
    }

    //guardar
    @PostMapping("/cita/guardar")
    public ResponseEntity<Date> guardar(@RequestBody Date Date){
        //parte de creacion de un Date 
        if(Date.getIdCita() == 0){
            service.save(Date);
            return ResponseEntity.ok(Date);
        }else{
            //parte de modificacin de un Date 
            Optional<Date> DateSinActu = service.findById(Date.getIdCita());
            //cogemos el objeto del optional 
            Date DateActu = DateSinActu.get();
            DateActu.setHora(Date.getHora());
            DateActu.setDuracion_Corte(Date.getDuracion_Corte());
            DateActu.setPrecio_Corte(Date.getPrecio_Corte());
            DateActu.setNombre_Corte(Date.getNombre_Corte());

            service.save(DateActu);
            return ResponseEntity.ok(DateActu);  
        }
            
    }
}
