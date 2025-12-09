package com.david.tfg.confing;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

// @Component
// public class JwtUtil {
//     private final String SECRET_KEY = "mi_clave_secreta"; // Cambiar a algo seguro y secreto
//     private final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hora

//     // Generar token
//     public String generateToken(String username,String rol) {
//         return Jwts.builder()
//                 .setSubject(username)
//                 .claim("rol", rol)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
//                 .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                 .compact();
//     }

//     // Obtener username del token
//     public String extractUsername(String token) {
//         return getClaims(token).getSubject();
//     }

//     // Validar token
//     public boolean validateToken(String token, String username) {
//         return extractUsername(token).equals(username) && !isTokenExpired(token);
//     }

//     //extrae y verifica la firma del token
//     private Claims getClaims(String token) {
//         return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//     }
//     //verifica si el token ha expirado
//     private boolean isTokenExpired(String token) {
//         return getClaims(token).getExpiration().before(new Date());
//     }

//         public String extractRole(String token) {
//         return getClaims(token).get("rol", String.class);
//     }
// @Component
// public class JwtUtil {

//     // ✅ Clave secreta larga para HS256 (mínimo 256 bits)
//     private final String SECRET_KEY = "mi_clave_secreta_muy_larga_para_hs256_que_no_tenga_underscores";
//     private final SecretKey SECRET = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

//     private final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hora

//     // ==========================
//     // Generar token JWT
//     // ==========================
//     public String generateToken(String username, String rol) {
//         return Jwts.builder()
//                 .setSubject(username)
//                 .claim("rol", rol)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
//                 .signWith(SECRET, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     // ==========================
//     // Obtener username del token
//     // ==========================
//     public String extractUsername(String token) {
//         return getClaims(token).getSubject();
//     }

//     // ==========================
//     // Obtener rol del token
//     // ==========================
//     public String extractRole(String token) {
//         return getClaims(token).get("rol", String.class);
//     }

//     // ==========================
//     // Validar token
//     // ==========================
//     public boolean validateToken(String token, String username) {
//         return extractUsername(token).equals(username) && !isTokenExpired(token);
//     }

//     // ==========================
//     // Verificar expiración
//     // ==========================
//     private boolean isTokenExpired(String token) {
//         return getClaims(token).getExpiration().before(new Date());
//     }

//     // ==========================
//     // Extraer claims del token
//     // ==========================
//     private Claims getClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(SECRET)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mi_clave_secreta_muy_larga_para_hs256_que_no_tenga_underscores";
    private final SecretKey SECRET = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private final long EXPIRATION_MS = 1000L * 60 * 60 * 24 * 7; // 7 días

    // ==========================
    // Generar token JWT
    // ==========================
    public String generateToken(Integer idUsuario, String username, String rol) {
        return Jwts.builder()
                .claim("idUsuario", idUsuario)
                .setSubject(username)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

    // ==========================
    // Extraer idUsuario del token
    // ==========================
    public Integer extractUserId(String token) {
        return getClaims(token).get("idUsuario", Integer.class);
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getClaims(token).get("rol", String.class);
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
