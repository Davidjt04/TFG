package com.david.tfg.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.tfg.entities.Article;
import com.david.tfg.entities.Cart;

public interface RepoCart extends JpaRepository <Cart, Integer>{

}
