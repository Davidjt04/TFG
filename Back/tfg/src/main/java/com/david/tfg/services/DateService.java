package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.Date;
import com.david.tfg.interfaces.Crud;

import org.springframework.stereotype.Service;

import com.david.tfg.repos.RepoDate;
@Service
public class DateService implements Crud <Date,Integer>{
    //inyeccion de dependencias
    private final RepoDate repo;

    public DateService(RepoDate repo) {
        this.repo = repo;
    }

    @Override
    public void save(Date entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Date> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Date> findAll() {
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
