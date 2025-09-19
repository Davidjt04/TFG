package com.david.tfg.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.tfg.entities.Article;

public interface RepoArticle extends JpaRepository <Article, Integer>{

}
