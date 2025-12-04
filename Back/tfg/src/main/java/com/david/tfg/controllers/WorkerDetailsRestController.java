package com.david.tfg.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.WorkerDetail;
import com.david.tfg.services.WorkerDetailService;



// @CrossOrigin(origins = "*")
// @RestController
// public class WorkerDetailsRestController {
//  //Inyectamos el servicio
//     private final WorkerDetailService service;

//     public WorkerDetailsRestController(WorkerDetailService service) {
//         this.service = service;
//     }

//     //se muestran todos los WorkerDetail
//     @GetMapping("/detallesTrabajador/lista")
//     public List<WorkerDetail> lista(){
//         //va a sacar una lista de articulos 
//         return this.service.findAll();
//     }

//     @DeleteMapping("/detallesTrabajador/borrar/{id}")
//     public ResponseEntity<WorkerDetail> borrar(@PathVariable Integer id){
//         //va a borrar un WorkerDetail
//         if(service.existsById(id)){  
//           service.deleteById(id);
//           //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
//             return ResponseEntity.noContent().build(); 
//         }
//         //noFound no se ha encontrado, codigo de error 404
//         return ResponseEntity.notFound().build();
//     }

// @DeleteMapping("/detallesTrabajador/borrar/{id}")
// public ResponseEntity<Void> borrar(@PathVariable Integer id){

//     Optional<WorkerDetail> opt = service.findById(id);
//     if (opt.isEmpty()) {
//         return ResponseEntity.notFound().build();
//     }

//     WorkerDetail detalle = opt.get();

//     ✅ ROMPER RELACIONES ANTES DE BORRAR
//     if (detalle.getUser() != null) {
//         detalle.getUser().setWorkerDetail(null);
//     }

//     if (detalle.getWorkerCut() != null) {
//         detalle.getWorkerCut().setWorkerDetail(null);
//     }

//     ✅ AHORA SÍ PUEDES BORRAR
//     service.deleteById(id);

//     return ResponseEntity.noContent().build();
// }


//  @DeleteMapping("/borrar/{id}")
//     public ResponseEntity<String> borrarTrabajador(@PathVariable Integer id) {
//         try {
//            service.borrarWorkerDetail(id);
//             return ResponseEntity.ok("Trabajador eliminado correctamente");
//         } catch (RuntimeException e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//         }
//     }


    // //editar
    // @GetMapping("/detallesTrabajador/editar/{id}")
    // public ResponseEntity<WorkerDetail> editar(@PathVariable Integer id) {
    // Optional<WorkerDetail> WorkerDetailOpt = service.findById(id);
    //     if (WorkerDetailOpt.isPresent()) {
    //         WorkerDetail WorkerDetail = WorkerDetailOpt.get();
    //         return ResponseEntity.ok(WorkerDetail);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    

    // //crear
    // @PostMapping("/detallesTrabajador/crear")
    // public ResponseEntity<WorkerDetail> crear(){
    //     //va a crear un WorkerDetail
    //     return ResponseEntity.ok(new WorkerDetail());
    // }

    // //guardar
    // @PostMapping("/detallesTrabajador/guardar")
    // public ResponseEntity<WorkerDetail> guardar(@RequestBody WorkerDetail WorkerDetail){
    //     //parte de creacion de un articulo 
    //     if(WorkerDetail.getIdDetalle_Trabajador() == 0){
    //         service.save(WorkerDetail);
    //         return ResponseEntity.ok(WorkerDetail);
    //     }else{
    //         //parte de modificacin de un WorkerDetail 
    //         Optional<WorkerDetail> ProductoSinActu = service.findById(WorkerDetail.getIdDetalle_Trabajador());
    //         //cogemos el objeto del optional 
    //         WorkerDetail productoActu = ProductoSinActu.get();
    //         productoActu.setHorario_Trabajador(WorkerDetail.getHorario_Trabajador());
    //         productoActu.setEspecializacion(WorkerDetail.getEspecializacion());
    //         productoActu.setAusencias(WorkerDetail.getAusencias());
    //         productoActu.setImagen(WorkerDetail.getImagen());
    //         productoActu.setNombre(WorkerDetail.getNombre());

    //         //meter los partidos asociados 
    //         service.save(productoActu);
    //         return ResponseEntity.ok(productoActu);  
    //     }
            
    // }
    @CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detallesTrabajador")
public class WorkerDetailsRestController {

    private final WorkerDetailService service;

    public WorkerDetailsRestController(WorkerDetailService service) {
        this.service = service;
    }

    // 🔹 Listar todos
    @GetMapping("/lista")
    public List<WorkerDetail> lista() {
        return service.findAll();
    }

    // 🔹 Crear un WorkerDetail vacío
    @PostMapping("/crear")
    public ResponseEntity<WorkerDetail> crear() {
        return ResponseEntity.ok(new WorkerDetail());
    }

    // 🔹 Guardar/editar un WorkerDetail
    @PostMapping("/guardar")
    public ResponseEntity<WorkerDetail> guardar(@RequestBody WorkerDetail workerDetail) {
        if (workerDetail.getIdDetalle_Trabajador() == null || workerDetail.getIdDetalle_Trabajador() == 0) {
            service.save(workerDetail);
            return ResponseEntity.ok(workerDetail);
        } else {
            Optional<WorkerDetail> opt = service.findById(workerDetail.getIdDetalle_Trabajador());
            if (opt.isPresent()) {
                WorkerDetail actual = opt.get();
                actual.setHorario_Trabajador(workerDetail.getHorario_Trabajador());
                actual.setEspecializacion(workerDetail.getEspecializacion());
                actual.setAusencias(workerDetail.getAusencias());
                actual.setImagen(workerDetail.getImagen());
                actual.setNombre(workerDetail.getNombre());
                service.save(actual);
                return ResponseEntity.ok(actual);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    // 🔹 Editar (obtener por id)
    @GetMapping("/editar/{id}")
    public ResponseEntity<WorkerDetail> editar(@PathVariable Integer id) {
        Optional<WorkerDetail> opt = service.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Borrar seguro
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        try {
            service.borrarWorkerDetail(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
