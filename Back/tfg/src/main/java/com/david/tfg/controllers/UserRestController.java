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

import com.david.tfg.entities.User;
import com.david.tfg.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Cliente")
public class UserRestController {
 //Inyectamos el servicio
    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    

    //se muestran todos los usuarios
    @GetMapping("/lista")
    public List<User> lista(){
        //va a sacar una lista de usuarios 
        return this.service.findAll();
    }

    @GetMapping("/borrar/{id}")
    public ResponseEntity<User> borrar(@PathVariable Integer id){
        //va a borrar un user
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/editar/{id}")
    public ResponseEntity<User> editar(@PathVariable Integer id) {
    Optional<User> userOpt = service.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/crear")
    public ResponseEntity<User> crear(){
        //va a crear un usuario
        return ResponseEntity.ok(new User());
    }

    //guardar
    @PostMapping("/guardar")
    public ResponseEntity<User> guardar(@RequestBody User user){
        //parte de creacion de un articulo 
        if(user.getIdUsuario()== 0){
            service.save(user);
            return ResponseEntity.ok(user);
        }else{
            //parte de modificacin de un arbitro 
            Optional<User> userSinActu = service.findById(user.getIdUsuario());
            //cogemos el objeto del optional 
            User userActu = userSinActu.get();
            userActu.setNombreUsuario(user.getNombreUsuario());
            userActu.setContrasenia(user.getContrasenia());
            userActu.setEmail(user.getContrasenia());
            userActu.setRol(user.getRol());


            //meter los partidos asociados 
            service.save(userActu);
            return ResponseEntity.ok(userActu);  
        }
            
    }
    
}
