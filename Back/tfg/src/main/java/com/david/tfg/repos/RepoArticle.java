package com.david.tfg.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.tfg.entities.Article;

public interface RepoArticle extends JpaRepository <Article, Integer>{
    @Override
    Optional<Article> findById(Integer idArticulo);

}
