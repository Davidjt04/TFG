package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.Services;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoService;


@Service
public class ServicesService implements Crud <Services,Integer>{
//inyeccion de dependencias
    private final RepoService repo;

    public ServicesService(RepoService repo) {
        this.repo = repo;
    }
    @Override
    public void save(Services entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Services> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Services> findAll() {
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
