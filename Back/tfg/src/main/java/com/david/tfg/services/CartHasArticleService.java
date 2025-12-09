package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.CartHasArticle;
import com.david.tfg.entities.IDCartHasArticle;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoCartHasArticle;

@Service

public class CartHasArticleService implements Crud <CartHasArticle,IDCartHasArticle> {
 //inyeccion de dependencias
    private final RepoCartHasArticle repo;

    public CartHasArticleService(RepoCartHasArticle repo) {
        this.repo = repo;
    }

    @Override
    public void save(CartHasArticle entity) {
        repo.save(entity);
    }

    @Override
    public Optional<CartHasArticle> findById(IDCartHasArticle id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(IDCartHasArticle id) {
        return this.repo.existsById(id);

    }

    @Override
    public List<CartHasArticle> findAll() {
        return this.repo.findAll();
    }

    @Override
    public void deleteById(IDCartHasArticle id) {
        this.repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repo.deleteAll();

    }

   

}