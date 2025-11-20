package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.FinalCite;
import com.david.tfg.interfaces.Crud;

import org.springframework.stereotype.Service;

import com.david.tfg.repos.RepoFinalCite;
@Service
public class FinalCiteService implements Crud <FinalCite,Integer>{
    //inyeccion de dependencias
    private final RepoFinalCite repo;

    public FinalCiteService(RepoFinalCite repo) {
        this.repo = repo;
    }

    @Override
    public void save(FinalCite entity) {
        repo.save(entity);
    }

    @Override
    public Optional<FinalCite> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<FinalCite> findAll() {
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
