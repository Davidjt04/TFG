package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.WorkerDetail;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoWorkerDetail;

import jakarta.transaction.Transactional;

@Service
public class WorkerDetailService implements Crud<WorkerDetail, Integer> {

    private final RepoWorkerDetail repo;

    public WorkerDetailService(RepoWorkerDetail repo) {
        this.repo = repo;
    }

    @Override
    public void save(WorkerDetail entity) {
        repo.save(entity);
    }

    @Override
    public Optional<WorkerDetail> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repo.existsById(id);
    }

    @Override
    public List<WorkerDetail> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    // 🔹 Método seguro para borrar un WorkerDetail
    @Transactional
    public void borrarWorkerDetail(Integer id) {
        WorkerDetail detalle = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado con id " + id));

        // Romper relación con User
        if (detalle.getUser() != null) {
            detalle.getUser().setWorkerDetail(null);
            detalle.setUser(null);
        }

        // Romper relación con WorkerCut
        if (detalle.getWorkerCut() != null) {
            detalle.getWorkerCut().setWorkerDetail(null);
            detalle.setWorkerCut(null);
        }

        // Borrar horarios asociados
        if (detalle.getHorarios() != null) {
            detalle.getHorarios().forEach(h -> h.setDetalleTrabajador(null));
            detalle.getHorarios().clear();
        }

        // Ahora sí se puede borrar
        repo.delete(detalle);
    }



}
