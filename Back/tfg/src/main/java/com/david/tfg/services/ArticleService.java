package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.Article;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoArticle;

public class ArticleService implements Crud <Article,Integer> {
    //inyeccion de dependencias
    private final RepoArticle repo;

    public ArticleService(RepoArticle repo) {
        this.repo = repo;
    }

    @Override
    public void save(Article entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Article> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Article> findAll() {
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
