package com.david.tfg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.david.tfg.entities.Article;
import com.david.tfg.entities.Cart;
import com.david.tfg.entities.CartArticleDTO;
import com.david.tfg.entities.CartDTO;
import com.david.tfg.entities.CartHasArticle;
import com.david.tfg.entities.IDCartHasArticle;
import com.david.tfg.entities.User;
import com.david.tfg.repos.RepoCartHasArticle;

@Service
public class CartHasArticleService {

    private final RepoCartHasArticle repo;
    private final CartService cartService;
    private final ArticleService articleService;
    private final UserService usuarioService; // <-- inyectamos UserService



    public CartHasArticleService(RepoCartHasArticle repo, CartService cartService, ArticleService articleService, UserService usuarioService) {
        this.repo = repo;
        this.cartService = cartService;
        this.articleService = articleService;
        this.usuarioService = usuarioService;
    }

    


    public CartHasArticle addArticleToCart(CartArticleDTO dto) {
        int idUsuario = dto.getIdUsuario();
        int idArticulo = dto.getIdArticulo();
        int cantidad = dto.getCantidad();

        System.out.println("📌 Añadiendo artículo al carrito: usuario=" + idUsuario + ", articulo=" + idArticulo + ", cantidad=" + cantidad);

        // 1️⃣ Obtener carrito del usuario
        Cart cart = cartService.findByUsuarioId(idUsuario).orElseGet(() -> {
            Cart nuevo = new Cart();
            nuevo.setCantidad_Total(0);
            User user = usuarioService.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("❌ Usuario no encontrado: " + idUsuario));
            nuevo.setUser(user);
            cartService.save(nuevo);
            return nuevo;
        });

        // 2️⃣ Obtener artículo
        Article article = articleService.findById(idArticulo)
                .orElseThrow(() -> new RuntimeException("❌ Artículo no encontrado: " + idArticulo));

        // 3️⃣ Verificar si ya existe
        Optional<CartHasArticle> existing = repo.findByCartAndArticleEntities(cart, article);

        CartHasArticle cha;
        if (existing.isPresent()) {
            cha = existing.get();
            cha.setCantidad(cha.getCantidad() + cantidad);
        } else {
            IDCartHasArticle id = new IDCartHasArticle(cart.getIdCarrito(), article.getIdArticulo());
            cha = new CartHasArticle();
            cha.setId(id);
            cha.setCart(cart);
            cha.setArticle(article);
            cha.setCantidad(cantidad);
        }

        // Guardar y actualizar total
        repo.save(cha);
        cartService.actualizarCantidadTotal(cart);

        return cha;
    }

    // --- Método para listar artículos de un usuario ---
    public List<CartHasArticle> getCartArticles(int idUsuario) {
        return repo.findByCartUserIdUsuario(idUsuario);
    }
}

