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

import com.david.tfg.entities.CartHasArticle;
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
}
