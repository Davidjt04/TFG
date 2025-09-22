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
// import com.david.tfg.services.ArticleService;



// @CrossOrigin(origins = "*")
// @RestController
// public class DateController {
//  //Inyectamos el servicio
//     private final DateService service;

//     public ArticleRestController(DateService service) {
//         this.service = service;
//     }

//     //se muestran todos los articulos
//     @GetMapping("/articulo/lista")
//     public List<Article> lista(){
//         //va a sacar una lista de articulos 
//         return this.service.findAll();
//     }

//     @GetMapping("/articulo/borrar/{id}")
//     public ResponseEntity<Article> borrar(@PathVariable Integer id){
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
//     @GetMapping("/articulo/editar/{id}")
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
//     @PostMapping("/articulo/crear")
//     public ResponseEntity<Article> crear(){
//         //va a crear un producto
//         return ResponseEntity.ok(new Article());
//     }

//     //guardar
//     @PostMapping("/articulo/guardar")
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
