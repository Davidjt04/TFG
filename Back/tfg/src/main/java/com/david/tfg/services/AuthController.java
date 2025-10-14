package com.david.tfg.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.User;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    //Inyectamos el servicio
    private final UserService userService;

    // Constructor
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User usuario) {

        try {
            User nuevoUsuario = userService.validaRegistro(usuario);
            return ResponseEntity.ok("El usuario " + nuevoUsuario.getNombreUsuario() + " se ha registrado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

        @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User usuario) {
        //se tienen que validar 
        try {
            User nuevoUsuario = userService.validaLogin(usuario);
            return ResponseEntity.ok("El usuario " + nuevoUsuario.getNombreUsuario() + " ha iniciado sesión con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
