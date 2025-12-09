package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.tfg.entities.Article;
import com.david.tfg.entities.Cart;
import com.david.tfg.entities.CartHasArticle;
import com.david.tfg.entities.CartItemDto;
import com.david.tfg.entities.IDCartHasArticle;
import com.david.tfg.interfaces.Crud;
import com.david.tfg.repos.RepoCartHasArticle;

// @Service

// public class CartHasArticleService implements Crud <CartHasArticle,IDCartHasArticle> {
//  //inyeccion de dependencias
//     private final RepoCartHasArticle repo;

//     public CartHasArticleService(RepoCartHasArticle repo) {
//         this.repo = repo;
//     }

//     @Override
//     public void save(CartHasArticle entity) {
//         repo.save(entity);
//     }

//     @Override
//     public Optional<CartHasArticle> findById(IDCartHasArticle id) {
//         return this.repo.findById(id);
//     }

//     @Override
//     public boolean existsById(IDCartHasArticle id) {
//         return this.repo.existsById(id);

//     }

//     @Override
//     public List<CartHasArticle> findAll() {
//         return this.repo.findAll();
//     }

//     @Override
//     public void deleteById(IDCartHasArticle id) {
//         this.repo.deleteById(id);
//     }

//     @Override
//     public void deleteAll() {
//         this.repo.deleteAll();

//     }

   

// }
@Service
public class CartHasArticleService implements Crud<CartHasArticle, IDCartHasArticle> {

    private final RepoCartHasArticle repo;
    private final CartService cartService;
    private final ArticleService articleService;

    public CartHasArticleService(RepoCartHasArticle repo, CartService cartService, ArticleService articleService) {
        this.repo = repo;
        this.cartService = cartService;
        this.articleService = articleService;
    }

    // Método para guardar desde DTO
    public void saveFromDto(CartItemDto dto) {
        int cartId = dto.getCartId();
        int articleId = dto.getArticleId();
        int quantity = dto.getQuantity();

        IDCartHasArticle id = new IDCartHasArticle(cartId, articleId);
        Optional<CartHasArticle> existenteOpt = repo.findById(id);

        Cart cart = cartService.findById(cartId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Article article = articleService.findById(articleId).orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        CartHasArticle cha;
        if (existenteOpt.isPresent()) {
            // Sobrescribir cantidad
            cha = existenteOpt.get();
            cha.setCantidad(quantity);
        } else {
            // Crear nuevo
            cha = new CartHasArticle();
            cha.setId(id);
            cha.setCart(cart);
            cha.setArticle(article);
            cha.setCantidad(quantity);
        }

        repo.save(cha);

        // Opcional: actualizar cantidad total en el carrito
        int total = cart.getCartHasArticles().stream().mapToInt(CartHasArticle::getCantidad).sum();
        cart.setCantidad_Total(total);
        cartService.save(cart);
    }

    @Override
    public void save(CartHasArticle entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<CartHasArticle> findById(IDCartHasArticle id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsById(IDCartHasArticle id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CartHasArticle> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(IDCartHasArticle id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    // Obtener el carrito de un usuario por su ID
public Optional<Cart> getCartByUsuarioId(int userId) {
    return cartService.findByUsuarioId(userId);
}
}
