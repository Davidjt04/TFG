package com.david.tfg.services;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.stereotype.Service;

// import com.david.tfg.entities.Article;
// import com.david.tfg.entities.Cart;
// import com.david.tfg.entities.CartHasArticle;
// import com.david.tfg.entities.CartItemDto;
// import com.david.tfg.entities.IDCartHasArticle;
// import com.david.tfg.interfaces.Crud;
// import com.david.tfg.repos.RepoCartHasArticle;

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
// @Service
// public class CartHasArticleService implements Crud<CartHasArticle, IDCartHasArticle> {

//     private final RepoCartHasArticle repo;
//     private final CartService cartService;
//     private final ArticleService articleService;

//     public CartHasArticleService(RepoCartHasArticle repo, CartService cartService, ArticleService articleService) {
//         this.repo = repo;
//         this.cartService = cartService;
//         this.articleService = articleService;
//     }

//     // Método para guardar desde DTO
//     public void saveFromDto(CartItemDto dto) {
//         int cartId = dto.getCartId();
//         int articleId = dto.getArticleId();
//         int quantity = dto.getQuantity();

//         IDCartHasArticle id = new IDCartHasArticle(cartId, articleId);
//         Optional<CartHasArticle> existenteOpt = repo.findById(id);

//         Cart cart = cartService.findById(cartId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
//         Article article = articleService.findById(articleId).orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

//         CartHasArticle cha;
//         if (existenteOpt.isPresent()) {
//             // Sobrescribir cantidad
//             cha = existenteOpt.get();
//             cha.setCantidad(quantity);
//         } else {
//             // Crear nuevo
//             cha = new CartHasArticle();
//             cha.setId(id);
//             cha.setCart(cart);
//             cha.setArticle(article);
//             cha.setCantidad(quantity);
//         }

//         repo.save(cha);

//         // Opcional: actualizar cantidad total en el carrito
//         int total = cart.getCartHasArticles().stream().mapToInt(CartHasArticle::getCantidad).sum();
//         cart.setCantidad_Total(total);
//         cartService.save(cart);
//     }

//     @Override
//     public void save(CartHasArticle entity) {
//         throw new UnsupportedOperationException("Not supported yet.");
//     }

//     @Override
//     public Optional<CartHasArticle> findById(IDCartHasArticle id) {
//         throw new UnsupportedOperationException("Not supported yet.");
//     }

//     @Override
//     public boolean existsById(IDCartHasArticle id) {
//         throw new UnsupportedOperationException("Not supported yet.");
//     }

//     @Override
//     public List<CartHasArticle> findAll() {
//         throw new UnsupportedOperationException("Not supported yet.");
//     }

//     @Override
//     public void deleteById(IDCartHasArticle id) {
//         throw new UnsupportedOperationException("Not supported yet.");
//     }

//     @Override
//     public void deleteAll() {
//         throw new UnsupportedOperationException("Not supported yet.");
//     }
//     // Obtener el carrito de un usuario por su ID
// public Optional<Cart> getCartByUsuarioId(int userId) {
//     return cartService.findByUsuarioId(userId);
// }
// }

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

    // public CartHasArticle addArticleToCart(int idUsuario, int idArticulo, int cantidad) {
    //     System.out.println("📌 Añadiendo artículo al carrito: usuario=" + idUsuario + ", articulo=" + idArticulo + ", cantidad=" + cantidad);

    //     // 1️⃣ Obtener carrito del usuario
    //     Cart cart = cartService.findByUsuarioId(idUsuario).orElseGet(() -> {
    //         System.out.println("⚠️ Carrito no encontrado, creando uno nuevo para usuario " + idUsuario);
    //         Cart nuevo = new Cart();
    //         nuevo.setCantidad_Total(0);
    //         articleService.findById(idArticulo); // No hacemos nada con esto aún
    //         return cartService.findByUsuarioId(idUsuario).orElse(null); // Solo para cumplir la lógica
    //     });

    //     if (cart == null) {
    //         throw new RuntimeException("❌ Error al obtener o crear carrito para usuario " + idUsuario);
    //     }

    //     // 2️⃣ Obtener artículo
    //     Article article = articleService.findById(idArticulo).orElseThrow(() ->
    //         new RuntimeException("❌ Artículo no encontrado: " + idArticulo)
    //     );

    //     // 3️⃣ Verificar si ya existe el artículo en el carrito
    //     Optional<CartHasArticle> existing = repo.findByCartAndArticleEntities(cart, article);

    //     CartHasArticle cartHasArticle;
    //     if (existing.isPresent()) {
    //         cartHasArticle = existing.get();
    //         cartHasArticle.setCantidad(cartHasArticle.getCantidad() + cantidad);
    //         System.out.println("🔄 Artículo ya en carrito, actualizando cantidad a " + cartHasArticle.getCantidad());
    //     } else {
    //         IDCartHasArticle id = new IDCartHasArticle();
    //         id.setCarritoId(cart.getIdCarrito());
    //         id.setArticuloId(article.getIdArticulo());

    //         cartHasArticle = new CartHasArticle();
    //         cartHasArticle.setId(id);
    //         cartHasArticle.setCart(cart);
    //         cartHasArticle.setArticle(article);
    //         cartHasArticle.setCantidad(cantidad);

    //         System.out.println("✅ Artículo añadido al carrito con cantidad " + cantidad);
    //     }

    //     // Guardar
    //     repo.save(cartHasArticle);

    //     // Actualizar cantidad total del carrito
    //     cartService.actualizarCantidadTotal(cart);

    //     return cartHasArticle;
    // }

//     public CartHasArticle addArticleToCart(int idUsuario, int idArticulo, int cantidad) {
//     System.out.println("📌 Añadiendo artículo al carrito: usuario=" + idUsuario + ", articulo=" + idArticulo + ", cantidad=" + cantidad);

//     // 1️⃣ Obtener carrito del usuario
//     Cart cart = cartService.findByUsuarioId(idUsuario).orElseGet(() -> {
//         System.out.println("⚠️ Carrito no encontrado, creando uno nuevo para usuario " + idUsuario);
//         Cart nuevo = new Cart();
//         nuevo.setCantidad_Total(0);

//         // Recuperar usuario
//         User user = usuarioService.findById(idUsuario).orElseThrow(() -> 
//             new RuntimeException("❌ Usuario no encontrado: " + idUsuario)
//         );
//         nuevo.setUser(user);

//         // Guardar carrito recién creado
//         cartService.save(nuevo);
//         System.out.println("✅ Carrito creado y guardado con id: " + nuevo.getIdCarrito());

//         return nuevo;
//     });

//     // 2️⃣ Obtener artículo
//     Article article = articleService.findById(idArticulo).orElseThrow(() ->
//         new RuntimeException("❌ Artículo no encontrado: " + idArticulo)
//     );

//     // 3️⃣ Verificar si ya existe el artículo en el carrito
//     Optional<CartHasArticle> existing = repo.findByCartAndArticleEntities(cart, article);

//     CartHasArticle cartHasArticle;
//     if (existing.isPresent()) {
//         cartHasArticle = existing.get();
//         cartHasArticle.setCantidad(cartHasArticle.getCantidad() + cantidad);
//         System.out.println("🔄 Artículo ya en carrito, actualizando cantidad a " + cartHasArticle.getCantidad());
//     } else {
//         IDCartHasArticle id = new IDCartHasArticle(cart.getIdCarrito(), article.getIdArticulo());

//         cartHasArticle = new CartHasArticle();
//         cartHasArticle.setId(id);
//         cartHasArticle.setCart(cart);
//         cartHasArticle.setArticle(article);
//         cartHasArticle.setCantidad(cantidad);

//         System.out.println("✅ Artículo añadido al carrito con cantidad " + cantidad);
//     }

//     // Guardar
//     repo.save(cartHasArticle);

//     // Actualizar cantidad total del carrito
//     cartService.actualizarCantidadTotal(cart);

//     return cartHasArticle;
// }


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

