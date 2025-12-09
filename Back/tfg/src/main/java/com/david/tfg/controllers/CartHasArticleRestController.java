// package com.david.tfg.controllers;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.david.tfg.entities.CartHasArticle;
// import com.david.tfg.entities.CartItemDto;
// import com.david.tfg.entities.IDCartHasArticle;
// import com.david.tfg.services.CartHasArticleService;

// @CrossOrigin(origins = "*")
// @RestController
// @RequestMapping("/CartHasArticle")
// public class CartHasArticleRestsController {

//     private final CartHasArticleService service;

//     public CartHasArticleRestsController(CartHasArticleService service) {
//         this.service = service;
//     }

//     // ================= CLIENTE =================
//     @GetMapping("/CLIENTE/usuario")
//     public ResponseEntity<List<CartHasArticle>> carritoDelUsuarioCliente() {
//         Integer usuarioId = obtenerUsuarioLogueadoId(); // extraído del JWT
//         if (usuarioId == null) return ResponseEntity.status(401).build();

//         List<CartHasArticle> carrito = service.findByUsuarioId(usuarioId);
//         return ResponseEntity.ok(carrito);
//     }

//     @PostMapping("/CLIENTE/guardar")
//     public ResponseEntity<Void> guardarCliente(@RequestBody CartItemDto itemDto){ // Recibe el DTO
//         System.out.println("LOG CONTROLLER DTO: Recibido DTO con Carrito ID: " + itemDto.getCartId() + " Articulo ID: " + itemDto.getArticleId());
        
//         service.saveFromDto(itemDto); // Llama al método del servicio que usa el DTO
        
//         return ResponseEntity.ok().build(); // Devuelve 200 OK sin cuerpo
//     }

//     @DeleteMapping("/CLIENTE/borrar/{carritoId}/{articuloId}")
//     public ResponseEntity<Void> borrarCliente(@PathVariable Integer carritoId, @PathVariable Integer articuloId) {
//         // Crear la clave compuesta manualmente
//         IDCartHasArticle id = new IDCartHasArticle();
//         id.setCarritoId(carritoId);
//         id.setArticuloId(articuloId);

//         if(service.existsById(id)){
//             service.deleteById(id);
//             return ResponseEntity.noContent().build();
//         }
//         return ResponseEntity.notFound().build();
//     }

//     // ================= ADMIN =================
//     @GetMapping("/ADMIN/lista")
//     public List<CartHasArticle> listaTodosAdmin() {
//         return service.findAll();
//     }

//     @PostMapping("/ADMIN/guardar")
//     public ResponseEntity<CartHasArticle> guardarAdmin(@RequestBody CartHasArticle cartHasArticle){
//         service.save(cartHasArticle);
//         return ResponseEntity.ok(cartHasArticle);
//     }

//     @DeleteMapping("/ADMIN/borrar/{carritoId}/{articuloId}")
//     public ResponseEntity<Void> borrarAdmin(@PathVariable Integer carritoId, @PathVariable Integer articuloId){
//         IDCartHasArticle id = new IDCartHasArticle();
//         id.setCarritoId(carritoId);
//         id.setArticuloId(articuloId);

//         if(service.existsById(id)){
//             service.deleteById(id);
//             return ResponseEntity.noContent().build();
//         }
//         return ResponseEntity.notFound().build();
//     }
    
//     // ================= Método auxiliar =================
//     private Integer obtenerUsuarioLogueadoId() {
//         return 17;
//     }
// }
package com.david.tfg.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.david.tfg.entities.Cart;
import com.david.tfg.entities.CartArticleDTO;
import com.david.tfg.entities.CartDTO;
import com.david.tfg.entities.CartHasArticle;
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

    // 2️⃣ Listar artículos de usuario
    // @GetMapping("/carrito/{idUsuario}/articulos")
    // public ResponseEntity<List<CartHasArticle>> getCartArticles(@PathVariable int idUsuario) {
    //     List<CartHasArticle> articles = cartHasArticleService.getCartArticles(idUsuario);
    //     return ResponseEntity.ok(articles);
    // }
    @GetMapping("/carrito/{idUsuario}/articulos")
    public ResponseEntity<List<CartArticleDTO>> getCartArticles(@PathVariable int idUsuario) {
    // Obtenemos todos los CartHasArticle del usuario
    List<CartHasArticle> articles = cartHasArticleService.getCartArticles(idUsuario);

    // Convertimos a DTO para evitar referencias circulares
    List<CartArticleDTO> dtoList = articles.stream()
            .map(cha -> new CartArticleDTO(
                    cha.getCart().getUser() != null ? cha.getCart().getUser().getIdUsuario() : 0,
                    cha.getArticle().getIdArticulo(),
                    cha.getCantidad()
            ))
            .toList();

    return ResponseEntity.ok(dtoList);
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
}