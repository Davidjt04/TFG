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

// import com.david.tfg.entities.Article;
// import com.david.tfg.entities.CartHasArticle;
// import com.david.tfg.services.ArticleService;
// import com.david.tfg.services.CartHasArticleService;

// public class CartHasArticleController {
//     //Inyectamos el servicio
//     private final CartHasArticleService service;

//     public CartHasArticleController(CartHasArticleService service) {
//         this.service = service;
//     }

//     //se muestran todos los CartHasArticle
//     @GetMapping("/CartHasArticle/lista")
//     public List<CartHasArticle> lista(){
//         //va a sacar una lista de CartHasArticle 
//         return this.service.findAll();
//     }

//     @GetMapping("/CartHasArticle/borrar/{id}")
//     public ResponseEntity<CartHasArticle> borrar(@PathVariable Integer id){
//         //va a borrar un articulo
//         if(service.existsById(id)){  
//           service.deleteById(id);
//           //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
//             return ResponseEntity.noContent().build(); 
//         }
//         //noFound no se ha encontrado, codigo de error 404
//         return ResponseEntity.notFound().build();
//     }

//     //editar
//     @GetMapping("/CartHasArticle/editar/{id}")
//     public ResponseEntity<Article> editar(@PathVariable Integer id) {
//     Optional<Article> articuloOpt = service.findById(id);
//         if (articuloOpt.isPresent()) {
//             Article articulo = articuloOpt.get();
//             return ResponseEntity.ok(articulo);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     //crear
//     @PostMapping("/CartHasArticle/crear")
//     public ResponseEntity<Article> crear(){
//         //va a crear un producto
//         return ResponseEntity.ok(new Article());
//     }

//     //guardar
//     @PostMapping("/CartHasArticle/guardar")
//     public ResponseEntity<Article> guardar(@RequestBody Article articulo){
//         //parte de creacion de un articulo 
//         if(articulo.getIdArticulo() == 0){
//         //    return service.save(arbitro);
//             service.save(articulo);
//             return ResponseEntity.ok(articulo);
//         }else{
//             //parte de modificacin de un arbitro 
//             Optional<Article> ProductoSinActu = service.findById(articulo.getIdArticulo());
//             //cogemos el objeto del optional 
//             Article productoActu = ProductoSinActu.get();
//             productoActu.setNombre(articulo.getNombre());
//             productoActu.setDescripcion(articulo.getDescripcion());
//             productoActu.setImagen(articulo.getImagen());
//             productoActu.setPrecio(articulo.getPrecio());
//             productoActu.setCategoria(articulo.getCategoria());
//             productoActu.setCantidad(articulo.getCantidad());

//             //meter los partidos asociados 
//             service.save(productoActu);
//             return ResponseEntity.ok(productoActu);  
//         }
            
//     }
// }
