package com.david.tfg.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.david.tfg.entities.Article;
import com.david.tfg.entities.Cart;
import com.david.tfg.entities.CartHasArticle;
import com.david.tfg.entities.IDCartHasArticle;


public interface RepoCartHasArticle extends JpaRepository<CartHasArticle, IDCartHasArticle> {

    // 1. Obtener artículos del carrito de un usuario
    List<CartHasArticle> findByCartUserIdUsuario(Integer idUsuario);

    // 2. Ver si un artículo ya existe en el carrito
    @Query("SELECT cha FROM CartHasArticle cha WHERE cha.cart = :cart AND cha.article = :article")
    Optional<CartHasArticle> findByCartAndArticleEntities(@Param("cart") Cart cart, @Param("article") Article article);

    // 3. Listar artículos por carrito
    @Query("SELECT cha FROM CartHasArticle cha WHERE cha.cart = :cart")
    List<CartHasArticle> findByCart(@Param("cart") Cart cart);
}

