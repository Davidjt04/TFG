package com.david.tfg.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.Review;
import com.david.tfg.services.ReviewService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Review")

public class ReviewRestController {

    //Inyectamos el servicio
    private final ReviewService service;

    public ReviewRestController(ReviewService service) {
        this.service = service;
    }

    //se muestran todos las reseñas
    @GetMapping("/review/lista")
    public List<Review> lista(){
        //va a sacar una lista de reviews 
        return this.service.findAll();
    }

    @GetMapping("/review/borrar/{id}")
    public ResponseEntity<Review> borrar(@PathVariable Integer id){
        //va a borrar una Review
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/review/editar/{id}")
    public ResponseEntity<Review> editar(@PathVariable Integer id) {
    Optional<Review> reviewOpt = service.findById(id);
        if (reviewOpt.isPresent()) {
            Review review = reviewOpt.get();
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/review/crear")
    public ResponseEntity<Review> crear(){
        //va a crear un producto
        return ResponseEntity.ok(new Review());
    }

    //guardar
    @PostMapping("/review/guardar")
    public ResponseEntity<Review> guardar(@RequestBody Review review){
        //parte de creacion de una review 
        if(review.getIdReseña() == 0){
            service.save(review);
            return ResponseEntity.ok(review);
        }else{
            //parte de modificacin de una review 
            Optional<Review> reviewSinActu = service.findById(review.getIdReseña());
            //cogemos el objeto del optional 
            Review reviewActu = reviewSinActu.get();
            reviewActu.setEstrellas(review.getEstrellas());
            reviewActu.setHora(review.getHora());

            service.save(reviewActu);
            return ResponseEntity.ok(reviewActu);  
        }
            
    }
}
