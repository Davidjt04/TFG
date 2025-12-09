// // package com.david.tfg.controllers;

// // import java.util.List;
// // import java.util.Optional;

// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.CrossOrigin;
// // import org.springframework.web.bind.annotation.GetMapping;
// // import org.springframework.web.bind.annotation.PathVariable;
// // import org.springframework.web.bind.annotation.PostMapping;
// // import org.springframework.web.bind.annotation.RequestBody;
// // import org.springframework.web.bind.annotation.RestController;

// // import com.david.tfg.entities.Cart;
// import com.david.tfg.services.CartService;

// // @CrossOrigin(origins = "*")
// // @RestController
// // public class CartRestController {
// //     //Inyectamos el servicio
// //     private final CartService service;

// //     public CartRestController(CartService service) {
// //         this.service = service;
// //     }

// //     //se muestran todos los monederos
// //     @GetMapping("/monedero/lista")
// //     public List<Cart> lista(){
// //         //va a sacar una lista de monederos 
// //         return this.service.findAll();
// //     }

// //     @GetMapping("/monedero/borrar/{id}")
// //     public ResponseEntity<Cart> borrar(@PathVariable Integer id){
// //         //va a borrar un monederos
// //         if(service.existsById(id)){  
// //           service.deleteById(id);
// //           //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
// //             return ResponseEntity.noContent().build(); 
// //         }
// //         //noFound no se ha encontrado, codigo de error 404
// //         return ResponseEntity.notFound().build();
// //     }

// //     //editar
// //     @GetMapping("/monedero/editar/{id}")
// //     public ResponseEntity<Cart> editar(@PathVariable Integer id) {
// //     Optional<Cart> monederoOpt = service.findById(id);
// //         if (monederoOpt.isPresent()) {
// //             Cart monedero = monederoOpt.get();
// //             return ResponseEntity.ok(monedero);
// //         } else {
// //             return ResponseEntity.notFound().build();
// //         }
// //     }

// //     //crear
// //     @PostMapping("/monedero/crear")
// //     public ResponseEntity<Cart> crear(){
// //         //va a crear un producto
// //         return ResponseEntity.ok(new Cart());
// //     }

// //     //guardar
// //     @PostMapping("/monedero/guardar")
// //     public ResponseEntity<Cart> guardar(@RequestBody Cart monedero){
// //         //parte de creacion de un articulo 
// //         if(monedero.getIdCarrito() == 0){
// //         //    return service.save(arbitro);
// //             service.save(monedero);
// //             return ResponseEntity.ok(monedero);
// //         }else{
// //             //parte de modificacin de un arbitro 
// //             Optional<Cart> MonederoSinActu = service.findById(monedero.getIdCarrito());
// //             //cogemos el objeto del optional 
// //             Cart monederoActu = MonederoSinActu.get();
// //             monederoActu.setCantidad_Total(monedero.getCantidad_Total());

// //             //meter los partidos asociados 
// //             service.save(monederoActu);
// //             return ResponseEntity.ok(monederoActu);  
// //         }
            
// //     }
// // @GetMapping(value = "/carrito/usuario/{idUsuario}", produces = "application/json")
// // public ResponseEntity<Cart> obtenerCarritoPorUsuario(@PathVariable Integer idUsuario) {

// //     Optional<Cart> carrito = service.findByUsuarioId(idUsuario);

// //     if (carrito.isPresent()) {
// //         System.out.println("✅ Carrito encontrado: " + carrito.get().getIdCarrito());
// //         return ResponseEntity.ok(carrito.get());   // ✅ Devuelve el carrito correcto
// //     } else {
// //         System.out.println("❌ No se encontró carrito para usuario: " + idUsuario);
// //         return ResponseEntity.notFound().build();
// //     }
// // }




// // }
// package com.david.tfg.controllers;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.david.tfg.entities.Cart;
// import com.david.tfg.services.CartService;

// @CrossOrigin(origins = "*")
// @RestController
// public class CartRestController {
//     //Inyectamos el servicio
//     private final CartService service;

//     public CartRestController(CartService service) {
//         this.service = service;
//     }

//     //se muestran todos los monederos
//     @GetMapping("/monedero/lista")
//     public List<Cart> lista(){
//         //va a sacar una lista de monederos 
//         return this.service.findAll();
//     }

//     @GetMapping("/monedero/borrar/{id}")
//     public ResponseEntity<Cart> borrar(@PathVariable Integer id){
//         //va a borrar un monederos
//         if(service.existsById(id)){  
//           service.deleteById(id);
//           //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
//             return ResponseEntity.noContent().build(); 
//         }
//         //noFound no se ha encontrado, codigo de error 404
//         return ResponseEntity.notFound().build();
//     }

//     //editar
//     @GetMapping("/monedero/editar/{id}")
//     public ResponseEntity<Cart> editar(@PathVariable Integer id) {
//     Optional<Cart> monederoOpt = service.findById(id);
//         if (monederoOpt.isPresent()) {
//             Cart monedero = monederoOpt.get();
//             return ResponseEntity.ok(monedero);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     //crear
//     @PostMapping("/monedero/crear")
//     public ResponseEntity<Cart> crear(){
//         //va a crear un producto
//         return ResponseEntity.ok(new Cart());
//     }

//     //guardar
//     @PostMapping("/monedero/guardar")
//     public ResponseEntity<Cart> guardar(@RequestBody Cart monedero){
//         //parte de creacion de un articulo 
//         if(monedero.getIdCarrito() == 0){
//         //    return service.save(arbitro);
//             service.save(monedero);
//             return ResponseEntity.ok(monedero);
//         }else{
//             //parte de modificacin de un arbitro 
//             Optional<Cart> MonederoSinActu = service.findById(monedero.getIdCarrito());
//             //cogemos el objeto del optional 
//             Cart monederoActu = MonederoSinActu.get();
//             monederoActu.setCantidad_Total(monedero.getCantidad_Total());

//             //meter los partidos asociados 
//             service.save(monederoActu);
//             return ResponseEntity.ok(monederoActu);  
//         }
            
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
import com.david.tfg.services.CartService;


import com.david.tfg.entities.Cart;

@CrossOrigin(origins = "*")
@RestController
public class CartRestController {
    //Inyectamos el servicio
    private final CartService service;

    public CartRestController(CartService service) {
        this.service = service;
    }

    //se muestran todos los monederos
    @GetMapping("/monedero/lista")
    public List<Cart> lista(){
        //va a sacar una lista de monederos 
        return this.service.findAll();
    }

    @GetMapping("/monedero/borrar/{id}")
    public ResponseEntity<Cart> borrar(@PathVariable Integer id){
        //va a borrar un monederos
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/monedero/editar/{id}")
    public ResponseEntity<Cart> editar(@PathVariable Integer id) {
    Optional<Cart> monederoOpt = service.findById(id);
        if (monederoOpt.isPresent()) {
            Cart monedero = monederoOpt.get();
            return ResponseEntity.ok(monedero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/monedero/crear")
    public ResponseEntity<Cart> crear(){
        //va a crear un producto
        return ResponseEntity.ok(new Cart());
    }

    //guardar
    @PostMapping("/monedero/guardar")
    public ResponseEntity<Cart> guardar(@RequestBody Cart monedero){
        //parte de creacion de un articulo 
        if(monedero.getIdCarrito() == 0){
        //    return service.save(arbitro);
            service.save(monedero);
            return ResponseEntity.ok(monedero);
        }else{
            //parte de modificacin de un arbitro 
            Optional<Cart> MonederoSinActu = service.findById(monedero.getIdCarrito());
            //cogemos el objeto del optional 
            Cart monederoActu = MonederoSinActu.get();
            monederoActu.setCantidad_Total(monedero.getCantidad_Total());

            //meter los partidos asociados 
            service.save(monederoActu);
            return ResponseEntity.ok(monederoActu);  
        }
            
    }
}