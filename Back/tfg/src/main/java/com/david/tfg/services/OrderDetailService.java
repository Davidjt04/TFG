package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.Article;
import com.david.tfg.entities.OrderDetail;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoArticle;
import com.david.tfg.repos.RepoOrderDetail;

@Service
public class OrderDetailService implements Crud <OrderDetail,Integer>{
//inyeccion de dependencias
    private final RepoOrderDetail repo;

    public OrderDetailService(RepoOrderDetail repo) {
        this.repo = repo;
    }
    @Override
    public void save(OrderDetail entity) {
        repo.save(entity);
    }

    @Override
    public Optional<OrderDetail> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<OrderDetail> findAll() {
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
