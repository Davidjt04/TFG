package com.david.tfg.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.confing.JwtUtil;
import com.david.tfg.entities.LoginResponse;
import com.david.tfg.entities.User;
import com.david.tfg.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    //Inyectamos el servicio
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // Constructor
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
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

    //     @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody User usuario) {
    //     //se tienen que validar 
    //     try {
    //         User nuevoUsuario = userService.validaLogin(usuario);
    //         return ResponseEntity.ok("El usuario " + nuevoUsuario.getNombreUsuario() + " ha iniciado sesión con exito");
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

//     @PostMapping("/login")
// public ResponseEntity<?> login(@RequestBody User usuario) {
//     try {
//         //valida las credenciales del usuario
//         User usuarioBD = userService.validaLogin(usuario);
//         //se genera el token JWT
//         String token = jwtUtil.generateToken(usuarioBD.getNombreUsuario(), usuarioBD.getRol());
//         System.out.println("Token generado: " + token);
//         return ResponseEntity.ok(new LoginResponse(token, usuarioBD.getNombreUsuario(), usuarioBD.getRol()));
        
//     } catch (Exception e) {
//         return ResponseEntity.badRequest().body(e.getMessage());
//     }
// }

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User usuario) {
    try {
        User usuarioBD = userService.validaLogin(usuario);

        // 🔥 Ahora enviamos ID, nombre y rol al token
        String token = jwtUtil.generateToken(
                usuarioBD.getIdUsuario(),
                usuarioBD.getNombreUsuario(),
                usuarioBD.getRol()
        );

        System.out.println("Token generado: " + token);

        // 🔥 LoginResponse debe incluir idUsuario
        return ResponseEntity.ok(
            new LoginResponse(token, usuarioBD.getNombreUsuario(), usuarioBD.getRol(), usuarioBD.getIdUsuario())
        );

    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}


// Clase interna para la respuesta

}
