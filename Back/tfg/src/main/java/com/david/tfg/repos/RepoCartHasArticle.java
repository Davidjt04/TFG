package com.david.tfg.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.tfg.entities.CartHasArticle;
import com.david.tfg.entities.IDCartHasArticle;

public interface RepoCartHasArticle extends JpaRepository <CartHasArticle, IDCartHasArticle>{

}
