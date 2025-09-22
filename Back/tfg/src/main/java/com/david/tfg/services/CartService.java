package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.Article;
import com.david.tfg.entities.Cart;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoArticle;
import com.david.tfg.repos.RepoCart;

public class CartService implements Crud <Cart,Integer>{
//inyeccion de dependencias
    private final RepoCart repo;

    public CartService(RepoCart repo) {
        this.repo = repo;
    }
    @Override
    public void save(Cart entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Cart> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Cart> findAll() {
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
