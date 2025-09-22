package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.Purse;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoArticle;
import com.david.tfg.repos.RepoPurse;

public class PurseService implements Crud <Purse,Integer>{
//inyeccion de dependencias
    private final RepoPurse repo;

    public PurseService(RepoPurse repo) {
        this.repo = repo;
    }
    @Override
    public void save(Purse entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Purse> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Purse> findAll() {
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
