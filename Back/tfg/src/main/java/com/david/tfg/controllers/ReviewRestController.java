package com.david.tfg.controllers;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.Review;
import com.david.tfg.entities.User;
import com.david.tfg.services.ReviewService;
import com.david.tfg.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Review")

public class ReviewRestController {

    //Inyectamos el servicio
    private final ReviewService service;
    private final UserService userService;

    public ReviewRestController(ReviewService service, UserService userService) {
        this.service = service;
        this.userService = userService;
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
    // @PostMapping("/review/guardar")
    // public ResponseEntity<Review> guardar(@RequestBody Review review){
    //     //parte de creacion de una review 
    //     if(review.getIdResenia() == 0){
    //         service.save(review);
    //         return ResponseEntity.ok(review);
    //     }else{
    //         //parte de modificacin de una review 
    //         Optional<Review> reviewSinActu = service.findById(review.getIdResenia());
    //         //cogemos el objeto del optional 
    //         Review reviewActu = reviewSinActu.get();
    //         reviewActu.setEstrellas(review.getEstrellas());
    //         reviewActu.setHora(review.getHora());
    //         reviewActu.setResenia(review.getResenia());

    //         service.save(reviewActu);
    //         return ResponseEntity.ok(reviewActu);  
    //     }
            
    // }
//     @PostMapping("/review/guardar")
// public ResponseEntity<Review> guardar(@RequestBody Review review, @AuthenticationPrincipal UserDetails userDetails){
//     // Obtener usuario logueado desde UserDetails
//     User user = userService.findByNombreUsuario(userDetails.getUsername());

//     if(user == null){
//         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//     }

//     // Asignar el usuario logueado a la review
//     review.setUser(user);

//     if(review.getIdResenia() == 0){
//         service.save(review);
//         return ResponseEntity.ok(review);
//     } else {
//         Optional<Review> reviewSinActu = service.findById(review.getIdResenia());
//         if(reviewSinActu.isPresent()){
//             Review reviewActu = reviewSinActu.get();
//             reviewActu.setEstrellas(review.getEstrellas());
//             reviewActu.setHora(review.getHora());
//             reviewActu.setResenia(review.getResenia());
//             reviewActu.setUser(user); // actualizar usuario también
//             service.save(reviewActu);
//             return ResponseEntity.ok(reviewActu);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }
// }
@PostMapping("/review/guardar")
public ResponseEntity<Review> guardar(
        @RequestBody Review review, 
        @AuthenticationPrincipal UserDetails userDetails) {

    // 1️⃣ Validar que el usuario esté logueado
    if (userDetails == null || userDetails.getUsername() == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 2️⃣ Obtener usuario real desde la base de datos
    User user = userService.findByNombreUsuario(userDetails.getUsername());
    if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 3️⃣ Asignar usuario logueado a la review
    review.setUser(user);

    // 4️⃣ Validar campos mínimos de la review
    if (review.getEstrellas() < 0) review.setEstrellas(0);
    if (review.getEstrellas() > 5) review.setEstrellas(5);
    if (review.getHora() == null) review.setHora(LocalDateTime.now());

    // 5️⃣ Crear nueva review o actualizar existente
    if (review.getIdResenia() == 0) {
        service.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    } else {
        Optional<Review> reviewExistente = service.findById(review.getIdResenia());
        if (reviewExistente.isPresent()) {
            Review reviewActu = reviewExistente.get();
            reviewActu.setEstrellas(review.getEstrellas());
            reviewActu.setHora(review.getHora());
            reviewActu.setResenia(review.getResenia());
            reviewActu.setUser(user); // asegurar que el usuario esté actualizado
            service.save(reviewActu);
            return ResponseEntity.ok(reviewActu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


}
