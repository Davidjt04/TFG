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

import com.david.tfg.entities.Article;
import com.david.tfg.entities.PredefinedCut;
import com.david.tfg.services.ArticleService;
import com.david.tfg.services.PredefinedCutService;

@CrossOrigin(origins = "*")
@RestController
public class PredefinedCutController {
//Inyectamos el servicio
    private final PredefinedCutService service;

    public PredefinedCutController(PredefinedCutService service) {
        this.service = service;
    }

    //se muestran todos los articulos
    @GetMapping("/cortePredefinido/lista")
    public List<PredefinedCut> lista(){
        //va a sacar una lista de articulos 
        return this.service.findAll();
    }

    @GetMapping("/cortePredefinido/borrar/{id}")
    public ResponseEntity<PredefinedCut> borrar(@PathVariable Integer id){
        //va a borrar un articulo
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/cortePredefinido/editar/{id}")
    public ResponseEntity<PredefinedCut> editar(@PathVariable Integer id) {
    Optional<PredefinedCut> cortePredefinidoOpt = service.findById(id);
        if (cortePredefinidoOpt.isPresent()) {
            PredefinedCut cortePredefinido = cortePredefinidoOpt.get();
            return ResponseEntity.ok(cortePredefinido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/cortePredefinido/crear")
    public ResponseEntity<PredefinedCut> crear(){
        //va a crear un producto
        return ResponseEntity.ok(new PredefinedCut());
    }

    //guardar
    @PostMapping("/cortePredefinido/guardar")
    public ResponseEntity<PredefinedCut> guardar(@RequestBody PredefinedCut cortePredefinido){
        //parte de creacion de un articulo 
        if(cortePredefinido.getIdCorte_Predefinido() == 0){
        //    return service.save(arbitro);
            service.save(cortePredefinido);
            return ResponseEntity.ok(cortePredefinido);
        }else{
            //parte de modificacin de un arbitro 
            Optional<PredefinedCut> cortePredefinidoSinActu = service.findById(cortePredefinido.getIdCorte_Predefinido());
            //cogemos el objeto del optional 
            PredefinedCut cortePredefinidoActu = cortePredefinidoSinActu.get();
            cortePredefinidoActu.setNombre(cortePredefinido.getNombre());
            cortePredefinidoActu.setPrecio_Total(cortePredefinido.getPrecio_Total());
            cortePredefinidoActu.setDuracion_Base(cortePredefinido.getDuracion_Base());

            //meter los partidos asociados 
            service.save(cortePredefinidoActu);
            return ResponseEntity.ok(cortePredefinidoActu);  
        }
            
    }
}
