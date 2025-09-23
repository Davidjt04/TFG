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

import com.david.tfg.entities.WorkerCut;
import com.david.tfg.services.WorkerCutService;

@CrossOrigin(origins = "*")
@RestController
public class WorkerCutRestController {
 //Inyectamos el servicio
    private final WorkerCutService service;

    public WorkerCutRestController(WorkerCutService service) {
        this.service = service;
    }

    //se muestran todos los articulos
    @GetMapping("/cortesTrabajadores/lista")
    public List<WorkerCut> lista(){
        //va a sacar una lista de articulos 
        return this.service.findAll();
    }

    @GetMapping("/cortesTrabajadores/borrar/{id}")
    public ResponseEntity<WorkerCut> borrar(@PathVariable Integer id){
        //va a borrar un WorkerCut
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/cortesTrabajadores/editar/{id}")
    public ResponseEntity<WorkerCut> editar(@PathVariable Integer id) {
    Optional<WorkerCut> WorkerCutOpt = service.findById(id);
        if (WorkerCutOpt.isPresent()) {
            WorkerCut WorkerCut = WorkerCutOpt.get();
            return ResponseEntity.ok(WorkerCut);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/cortesTrabajadores/crear")
    public ResponseEntity<WorkerCut> crear(){
        //va a crear un producto
        return ResponseEntity.ok(new WorkerCut());
    }

    //guardar
    @PostMapping("/cortesTrabajadores/guardar")
    public ResponseEntity<WorkerCut> guardar(@RequestBody WorkerCut WorkerCut){
        //parte de creacion de un WorkerCut 
        if(WorkerCut.getIdCorte_Trabajador()== 0){
            service.save(WorkerCut);
            return ResponseEntity.ok(WorkerCut);
        }else{
            //parte de modificacin de un WorkerCut 
            Optional<WorkerCut> WorkerCutSinActu = service.findById(WorkerCut.getIdCorte_Trabajador());
            //cogemos el objeto del optional 
            WorkerCut WorkerCutActu = WorkerCutSinActu.get();
            WorkerCutActu.setDuracion(WorkerCut.getDuracion());
            WorkerCutActu.setPrecio(WorkerCut.getPrecio());

            //meter los partidos asociados 
            service.save(WorkerCutActu);
            return ResponseEntity.ok(WorkerCutActu);  
        }
            
    }
}
