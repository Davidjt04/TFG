package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.WorkerCut;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoWorkerCut;

@Service
public class WorkerCutService implements Crud <WorkerCut,Integer>{
//inyeccion de dependencias
    private final RepoWorkerCut repo;

    public WorkerCutService(RepoWorkerCut repo) {
        this.repo = repo;
    }
     @Override
    public void save(WorkerCut entity) {
        repo.save(entity);
    }

    @Override
    public Optional<WorkerCut> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<WorkerCut> findAll() {
        return this.repo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        this.repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repo.deleteAll();
    }
}
