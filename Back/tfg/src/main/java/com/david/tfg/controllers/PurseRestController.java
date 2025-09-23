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

import com.david.tfg.entities.Purse;
import com.david.tfg.services.PurseService;

@CrossOrigin(origins = "*")
@RestController
public class PurseRestController {
//Inyectamos el servicio
    private final PurseService service;

    public PurseRestController(PurseService service) {
        this.service = service;
    }

    //se muestran todas las carteras
    @GetMapping("/cartera/lista")
    public List<Purse> lista(){
        //va a sacar una lista de carteras 
        return this.service.findAll();
    }

    @GetMapping("/cartera/borrar/{id}")
    public ResponseEntity<Purse> borrar(@PathVariable Integer id){
        //va a borrar una cartera
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/cartera/editar/{id}")
    public ResponseEntity<Purse> editar(@PathVariable Integer id) {
    Optional<Purse> carteraOpt = service.findById(id);
        if (carteraOpt.isPresent()) {
            Purse cartera = carteraOpt.get();
            return ResponseEntity.ok(cartera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/cartera/crear")
    public ResponseEntity<Purse> crear(){
        //va a crear un monedero
        return ResponseEntity.ok(new Purse());
    }

    //guardar
    @PostMapping("/cartera/guardar")
    public ResponseEntity<Purse> guardar(@RequestBody Purse cartera){
        //parte de creacion de un monedero 
        if(cartera.getIdTransacccion_Monedero() == 0){
            service.save(cartera);
            return ResponseEntity.ok(cartera);
        }else{
            //parte de modificacin de un monedero 
            Optional<Purse> carteraSinActu = service.findById(cartera.getIdTransacccion_Monedero());
            //cogemos el objeto del optional 
            Purse carteraActu = carteraSinActu.get();
            carteraActu.setCantidadMonedero(cartera.getCantidadMonedero());

            service.save(carteraActu);
            return ResponseEntity.ok(carteraActu);  
        }
            
    }
}
