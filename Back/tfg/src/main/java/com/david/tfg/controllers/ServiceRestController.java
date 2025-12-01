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

import com.david.tfg.entities.Services;
import com.david.tfg.services.ServicesService;


@CrossOrigin(origins = "*")
@RestController
public class ServiceRestController {
    //Inyectamos el servicio
    private final ServicesService serviceService;

    public ServiceRestController(ServicesService service) {
        this.serviceService = service;
    }

    //se muestran todos los servicios
    @GetMapping("/service/lista")
    public List<Services> lista(){
        //va a sacar una lista de servicios 
        return this.serviceService.findAll();
    }

    @GetMapping("/service/borrar/{id}")
    public ResponseEntity<Services> borrar(@PathVariable Integer id){
        //va a borrar un servicio
        if(serviceService.existsById(id)){  
          serviceService.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/service/editar/{id}")
    public ResponseEntity<Services> editar(@PathVariable Integer id) {
    Optional<Services> serviceOpt = serviceService.findById(id);
        if (serviceOpt.isPresent()) {
            Services service = serviceOpt.get();
            return ResponseEntity.ok(service);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/service/crear")
    public ResponseEntity<Services> crear(){
        //va a crear un sevicio
        return ResponseEntity.ok(new Services());
    }

    //guardar
    // @PostMapping("/service/guardar")
    // public ResponseEntity<Services> guardar(@RequestBody Services service){
    //     //parte de creacion de un servicio 
    //     if(service.getIdServicio() == 0){
    //         serviceService.save(service);
    //         return ResponseEntity.ok(service);
    //     }else{
    //         //parte de modificacin de un servicio 
    //         Optional<Services> serviceSinActu = serviceService.findById(service.getIdServicio());
    //         //cogemos el objeto del optional 
    //         Services serviceActu = serviceSinActu.get();
    //         serviceActu.setNombre(service.getNombre());
    //         serviceActu.setPrecio(service.getPrecio());


    //         //meter los partidos asociados 
    //         serviceService.save(serviceActu);
    //         return ResponseEntity.ok(serviceActu);  
    //     }
            
    // }
    @PostMapping("/service/guardar")
public ResponseEntity<Services> guardar(@RequestBody Services service){
    if(service.getIdServicio() <= 0){ // incluir 0 y negativos
        serviceService.save(service);
        return ResponseEntity.ok(service);
    } else {
        Optional<Services> existing = serviceService.findById(service.getIdServicio());
        if(existing.isPresent()){
            Services toUpdate = existing.get();
            toUpdate.setNombre(service.getNombre());
            toUpdate.setPrecio(service.getPrecio());
            serviceService.save(toUpdate);
            return ResponseEntity.ok(toUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
}