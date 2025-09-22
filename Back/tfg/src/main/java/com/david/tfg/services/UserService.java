package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.Service;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoService;

public class UserService implements Crud <Service,Integer>{
 //inyeccion de dependencias
    private final RepoService repo;

    public UserService(RepoService repo) {
        this.repo = repo;
    }
     @Override
    public void save(Service entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Service> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Service> findAll() {
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
