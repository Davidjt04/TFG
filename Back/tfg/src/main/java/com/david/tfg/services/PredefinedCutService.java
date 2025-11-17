package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.PredefinedCut;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoPredefinedCut;

@Service
public class PredefinedCutService implements Crud <PredefinedCut,Integer>{
//inyeccion de dependencias
    private final RepoPredefinedCut repo;

    public PredefinedCutService(RepoPredefinedCut repo) {
        this.repo = repo;
    }
    @Override
    public void save(PredefinedCut entity) {
        repo.save(entity);
    }

    @Override
    public Optional<PredefinedCut> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<PredefinedCut> findAll() {
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
