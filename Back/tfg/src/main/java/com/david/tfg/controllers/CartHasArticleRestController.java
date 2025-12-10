package com.david.tfg.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.Cart;
import com.david.tfg.entities.CartArticleDTO;
import com.david.tfg.entities.CartDTO;
import com.david.tfg.entities.CartHasArticle;
import com.david.tfg.entities.CartHasArticleResponseDTO;
import com.david.tfg.services.CartHasArticleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/CLIENTE")
public class CartHasArticleRestController {

    private final CartHasArticleService cartHasArticleService;

    public CartHasArticleRestController(CartHasArticleService cartHasArticleService) {
        this.cartHasArticleService = cartHasArticleService;
    }

    // 1️⃣ Agregar artículo
    @PostMapping("/carrito/add")
    public ResponseEntity<CartDTO> addArticleToCart(@RequestBody CartArticleDTO dto) {
        CartHasArticle cha = cartHasArticleService.addArticleToCart(dto);
        Cart cart = cha.getCart();

        CartDTO cartDTO = new CartDTO();
        cartDTO.setIdCarrito(cart.getIdCarrito());
        cartDTO.setCantidadTotal(cart.getCantidad_Total());
        cartDTO.setUserId(cart.getUser() != null ? cart.getUser().getIdUsuario() : 0);

        List<CartArticleDTO> articulos = cart.getCartHasArticles() != null ?
            cart.getCartHasArticles().stream().map(ca ->
                new CartArticleDTO(cart.getUser() != null ? cart.getUser().getIdUsuario() : 0,
                                   ca.getArticle().getIdArticulo(),
                                   ca.getCantidad())
            ).toList() : List.of();

        cartDTO.setArticulos(articulos);

        return ResponseEntity.ok(cartDTO);
    }

//     @GetMapping("/carrito/{idUsuario}/articulos")
//     public ResponseEntity<List<CartArticleDTO>> getCartArticles(@PathVariable int idUsuario) {
//     // Obtenemos todos los CartHasArticle del usuario
//     List<CartHasArticle> articles = cartHasArticleService.getCartArticles(idUsuario);

//     // Convertimos a DTO para evitar referencias circulares
//     List<CartArticleDTO> dtoList = articles.stream()
//             .map(cha -> new CartArticleDTO(
//                     cha.getCart().getUser() != null ? cha.getCart().getUser().getIdUsuario() : 0,
//                     cha.getArticle().getIdArticulo(),
//                     cha.getCantidad()
//             ))
//             .toList();

//     return ResponseEntity.ok(dtoList);
// }
// @GetMapping("/carrito/{idUsuario}/articulos")
// public ResponseEntity<List<CartArticleDTO>> getCartArticles(@PathVariable int idUsuario) {
//     // Obtenemos todos los CartHasArticle del usuario
//     List<CartHasArticle> articles = cartHasArticleService.getCartArticles(idUsuario);

//     // 🔴 Log para inspección
//     System.out.println("🔴 Artículos crudos del backend para userId " + idUsuario + ":");
//     for (CartHasArticle cha : articles) {
//         System.out.println("    CartId: " + cha.getCart().getIdCarrito() +
//                            ", ArticleId: " + (cha.getArticle() != null ? cha.getArticle().getIdArticulo() : "null") +
//                            ", Nombre: " + (cha.getArticle() != null ? cha.getArticle().getNombre() : "null") +
//                            ", Cantidad: " + cha.getCantidad());
//     }

//     // Convertimos a DTO (actual)
//     List<CartArticleDTO> dtoList = articles.stream()
//             .map(cha -> new CartArticleDTO(
//                     cha.getCart().getUser() != null ? cha.getCart().getUser().getIdUsuario() : 0,
//                     cha.getArticle().getIdArticulo(),
//                     cha.getCantidad()
//             ))
//             .toList();

//     return ResponseEntity.ok(dtoList);
// }
@GetMapping("/carrito/{idUsuario}/articulos")
public ResponseEntity<List<CartHasArticleResponseDTO>> getCartArticles(@PathVariable int idUsuario) {
    List<CartHasArticle> articles = cartHasArticleService.getCartArticles(idUsuario);

    // Mapear cada CartHasArticle a nuestro DTO de salida
    List<CartHasArticleResponseDTO> response = articles.stream()
            .map(CartHasArticleResponseDTO::new)
            .toList();

    return ResponseEntity.ok(response);
}

    // 3️⃣ Obtener carrito completo
    @GetMapping("/carrito/{idUsuario}")
    public ResponseEntity<CartDTO> getCartDTOByUser(@PathVariable int idUsuario) {
        List<CartHasArticle> articles = cartHasArticleService.getCartArticles(idUsuario);
        CartDTO cartDTO = new CartDTO();

        if (!articles.isEmpty()) {
            Cart cart = articles.get(0).getCart();
            cartDTO.setIdCarrito(cart.getIdCarrito());
            cartDTO.setCantidadTotal(cart.getCantidad_Total());
            cartDTO.setUserId(cart.getUser() != null ? cart.getUser().getIdUsuario() : 0);

            List<CartArticleDTO> articulosDTO = articles.stream().map(ca ->
                new CartArticleDTO(cart.getUser() != null ? cart.getUser().getIdUsuario() : 0,
                                   ca.getArticle().getIdArticulo(),
                                   ca.getCantidad())
            ).toList();

            cartDTO.setArticulos(articulosDTO);
        }

        return ResponseEntity.ok(cartDTO);
    }
    @PutMapping("/carrito/{idCarrito}/articulo/{idArticulo}")
public ResponseEntity<CartHasArticleResponseDTO> actualizarCantidad(
        @PathVariable int idCarrito,
        @PathVariable int idArticulo,
        @RequestParam int cantidad) {

    CartHasArticle cha = cartHasArticleService.actualizarCantidad(idCarrito, idArticulo, cantidad);
    return ResponseEntity.ok(new CartHasArticleResponseDTO(cha));
}

}