package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.WorkerDetail;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoWorkerDetail;

public class WorkerDetailService implements Crud <WorkerDetail,Integer>{
    //inyeccion de dependencias
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
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<WorkerDetail> findAll() {
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
