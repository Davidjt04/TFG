package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.Article;
import com.david.tfg.entities.Order;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoOrder;

public class OrderService implements Crud <Order,Integer>{
 private final RepoOrder repo;

    public OrderService(RepoOrder repo) {
        this.repo = repo;
    }

    @Override
    public void save(Order entity) {
        repo.save(entity);

    }

    @Override
    public Optional<Order> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);

    }
    
    @Override
    public List<Order> findAll() {
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
