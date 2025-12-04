package com.david.tfg.confing;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.david.tfg.entities.User;
import com.david.tfg.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    // Constructor: Spring inyecta automáticamente las dependencias
    public JwtFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        // Leer el header "Authorization"
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // 2️⃣ Validar formato "Bearer <token>"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // quitamos "Bearer "
            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception e) {
                System.out.println("Error al extraer username del token: " + e.getMessage());
            }
        }

        // Validar token y autenticar usuario
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User usuario = userService.findByNombreUsuario(username);

            if (usuario != null && jwtUtil.validateToken(token, username)) {
                // Extraemos el rol del token
                String rol = jwtUtil.extractRole(token);

                // Creamos la autenticación para Spring Security
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + rol))
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Guardamos autenticación en el contexto de Spring Security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
