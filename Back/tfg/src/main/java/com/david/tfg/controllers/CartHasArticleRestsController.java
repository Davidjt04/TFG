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
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.Cart;
import com.david.tfg.entities.CartHasArticle;
import com.david.tfg.entities.CartItemDto;
import com.david.tfg.entities.IDCartHasArticle;
import com.david.tfg.services.CartHasArticleService;

@CrossOrigin(origins = "*")
@RestController
public class CartHasArticleRestsController {
    //Inyectamos el servicio
    private final CartHasArticleService service;

    public CartHasArticleRestsController(CartHasArticleService service) {
        this.service = service;
    }

    //se muestran todos los CartHasArticle
    @GetMapping("/CartHasArticle/lista")
    public List<CartHasArticle> lista(){
        //va a sacar una lista de CartHasArticle 
        return this.service.findAll();
    }

    @GetMapping("/CartHasArticle/borrar/{id}")
    public ResponseEntity<CartHasArticle> borrar(@PathVariable IDCartHasArticle id){
        //va a borrar un CartHasArticle
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/CartHasArticle/editar/{id}")
    public ResponseEntity<CartHasArticle> editar(@PathVariable IDCartHasArticle id) {
    Optional<CartHasArticle> CartHasArticleOpt = service.findById(id);
        if (CartHasArticleOpt.isPresent()) {
            CartHasArticle CartHasArticle = CartHasArticleOpt.get();
            return ResponseEntity.ok(CartHasArticle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/CartHasArticle/crear")
    public ResponseEntity<CartHasArticle> crear(){
        //va a crear un CartHasArticle
        return ResponseEntity.ok(new CartHasArticle());
    }

    //guardar
    @PostMapping("/CartHasArticle/guardar")
    public ResponseEntity<CartHasArticle> guardar(@RequestBody CartHasArticle CartHasArticle){
        //parte de creacion de un CartHasArticle 
        Optional<CartHasArticle> existente = service.findById(CartHasArticle.getId());
        if(existente.isPresent()){
            service.save(CartHasArticle);
            return ResponseEntity.ok(CartHasArticle);
        }else{
            //parte de modificacin de un CartHasArticle 
            Optional<CartHasArticle> CartHasArticleSinActu = service.findById(CartHasArticle.getId());
            //cogemos el objeto del optional 
            CartHasArticle CartHasArticleActu = CartHasArticleSinActu.get();
            CartHasArticleActu.setCantidad(CartHasArticle.getCantidad());

            service.save(CartHasArticleActu);
            return ResponseEntity.ok(CartHasArticleActu);  
        }
            
    }

    @PostMapping("/agregar")
    public ResponseEntity<Void> agregarArticulo(@RequestBody CartItemDto dto){
        service.saveFromDto(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<CartHasArticle>> obtenerCarritoUsuario(@PathVariable int userId){
        // Aquí se debe obtener el carrito del usuario
        Optional<Cart> carritoOpt = service.getCartByUsuarioId(userId);
        if (carritoOpt.isEmpty()) return ResponseEntity.notFound().build();

        List<CartHasArticle> items = carritoOpt.get().getCartHasArticles();
        return ResponseEntity.ok(items);
    }
}