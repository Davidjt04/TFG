package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import com.david.tfg.entities.Article;
import com.david.tfg.entities.Review;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoArticle;
import com.david.tfg.repos.RepoReview;

public class ReviewService implements Crud <Review,Integer>{
  //inyeccion de dependencias
    private final RepoReview repo;

    public ReviewService(RepoReview repo) {
        this.repo = repo;
    }
     @Override
    public void save(Review entity) {
        repo.save(entity);
    }

    @Override
    public Optional<Review> findById(Integer id) {
        return this.repo.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.repo.existsById(id);
    }

    @Override
    public List<Review> findAll() {
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
