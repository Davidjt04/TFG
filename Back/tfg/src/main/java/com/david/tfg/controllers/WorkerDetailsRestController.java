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
