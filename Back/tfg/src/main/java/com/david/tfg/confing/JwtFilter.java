// package com.david.tfg.confing;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.david.tfg.entities.User;
// import com.david.tfg.services.UserService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     private final JwtUtil jwtUtil;
//     private final UserService userService;

//     // Constructor: Spring inyecta automáticamente las dependencias
//     public JwtFilter(JwtUtil jwtUtil, UserService userService) {
//         this.jwtUtil = jwtUtil;
//         this.userService = userService;
//     }

    
//     @Override
// protected void doFilterInternal(HttpServletRequest request,
//                                 HttpServletResponse response,
//                                 FilterChain filterChain)
//                                 throws ServletException, IOException {

//     String path = request.getRequestURI();
//     if (path.startsWith("/img/") || path.startsWith("/css/") || path.startsWith("/js/")) {
//         filterChain.doFilter(request, response);
//         return;
//     }

//     final String authHeader = request.getHeader("Authorization");
//     String username = null;
//     String token = null;

//     System.out.println("🔐 JwtFilter interceptando request: " + request.getRequestURI());
//     System.out.println("   Authorization header: " + authHeader);

//     if (authHeader != null && authHeader.startsWith("Bearer ")) {
//         token = authHeader.substring(7);
//         try {
//             username = jwtUtil.extractUsername(token);
//             System.out.println("   Username extraído del token: " + username);
//         } catch (Exception e) {
//             System.out.println("⚠️ Error al extraer username del token: " + e.getMessage());
//         }
//     }

//     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//         User usuario = userService.findByNombreUsuario(username);

//         if (usuario != null && jwtUtil.validateToken(token, username)) {
//             String rol = jwtUtil.extractRole(token);
//             System.out.println("   Rol extraído del token: " + rol);

//             UsernamePasswordAuthenticationToken authToken =
//                     new UsernamePasswordAuthenticationToken(
//                             usuario,
//                             null,
//                             List.of(new SimpleGrantedAuthority("ROLE_" + rol))
//                     );

//             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//             SecurityContextHolder.getContext().setAuthentication(authToken);
//             System.out.println("✅ Authentication establecida: " + SecurityContextHolder.getContext().getAuthentication());
//         } else {
//             System.out.println("❌ Token inválido o usuario no encontrado");
//         }
//     } else if (username == null) {
//         System.out.println("⚠️ No se encontró username en el token");
//     } else {
//         System.out.println("⚠️ Authentication ya estaba establecida");
//     }

//     filterChain.doFilter(request, response);
// }

// }

// package com.david.tfg.confing;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.david.tfg.entities.User;
// import com.david.tfg.services.UserService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     private final JwtUtil jwtUtil;
//     private final UserService userService;

//     public JwtFilter(JwtUtil jwtUtil, UserService userService) {
//         this.jwtUtil = jwtUtil;
//         this.userService = userService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//                                     throws ServletException, IOException {

//         // 🔹 Ignorar preflight OPTIONS
//         if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//             response.setStatus(HttpServletResponse.SC_OK);
//             filterChain.doFilter(request, response);
//             return;
//         }

//         // 🔹 Ignorar recursos estáticos
//         String path = request.getRequestURI();
//         if (path.startsWith("/img/") || path.startsWith("/css/") || path.startsWith("/js/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         final String authHeader = request.getHeader("Authorization");
//         String username = null;
//         String token = null;

//         System.out.println("🔐 JwtFilter interceptando request: " + request.getRequestURI());
//         System.out.println("   Authorization header: " + authHeader);

//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             token = authHeader.substring(7);
//             try {
//                 username = jwtUtil.extractUsername(token);
//                 System.out.println("   Username extraído del token: " + username);
//             } catch (Exception e) {
//                 System.out.println("⚠️ Error al extraer username del token: " + e.getMessage());
//             }
//         }

//         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             User usuario = userService.findByNombreUsuario(username);

//             if (usuario != null && jwtUtil.validateToken(token, username)) {
//                 String rol = jwtUtil.extractRole(token);
//                 System.out.println("   Rol extraído del token: " + rol);

//                 UsernamePasswordAuthenticationToken authToken =
//                         new UsernamePasswordAuthenticationToken(
//                                 usuario,
//                                 null,
//                                 List.of(new SimpleGrantedAuthority("ROLE_" + rol))
//                         );

//                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(authToken);
//                 System.out.println("✅ Authentication establecida: " + SecurityContextHolder.getContext().getAuthentication());
//             } else {
//                 System.out.println("❌ Token inválido o usuario no encontrado");
//             }
//         } else if (username == null) {
//             System.out.println("⚠️ No se encontró username en el token");
//         } else {
//             System.out.println("⚠️ Authentication ya estaba establecida");
//         }

//         filterChain.doFilter(request, response);
//     }
// }
// package com.david.tfg.confing;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.david.tfg.entities.User;
// import com.david.tfg.services.UserService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     private final JwtUtil jwtUtil;
//     private final UserService userService;

//     public JwtFilter(JwtUtil jwtUtil, UserService userService) {
//         this.jwtUtil = jwtUtil;
//         this.userService = userService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//                                     throws ServletException, IOException {

//         // 🔹 Permitir CORS preflight OPTIONS sin JWT
//         if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//             response.setStatus(HttpServletResponse.SC_OK);
//             filterChain.doFilter(request, response);
//             return;
//         }

//         // 🔹 Ignorar recursos estáticos
//         String path = request.getRequestURI();
//         if (path.startsWith("/img/") || path.startsWith("/css/") || path.startsWith("/js/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         final String authHeader = request.getHeader("Authorization");
//         String username = null;
//         String token = null;

//         System.out.println("🔐 JwtFilter interceptando request: " + request.getRequestURI());
//         System.out.println("   Authorization header: " + authHeader);

//         // 🔹 Extraer token Bearer
//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             token = authHeader.substring(7);
//             try {
//                 username = jwtUtil.extractUsername(token);
//                 System.out.println("   Username extraído del token: " + username);
//             } catch (Exception e) {
//                 System.out.println("⚠️ Error al extraer username del token: " + e.getMessage());
//             }
//         }

//         // 🔹 Establecer autenticación si token válido
//         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             User usuario = userService.findByNombreUsuario(username);

//             if (usuario != null && jwtUtil.validateToken(token, username)) {
//                 String rol = jwtUtil.extractRole(token);
//                 System.out.println("   Rol extraído del token: " + rol);

//                 UsernamePasswordAuthenticationToken authToken =
//                         new UsernamePasswordAuthenticationToken(
//                                 usuario,
//                                 null,
//                                 List.of(new SimpleGrantedAuthority("ROLE_" + rol))
//                         );

//                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(authToken);

//                 System.out.println("✅ Authentication establecida: " + SecurityContextHolder.getContext().getAuthentication());
//             } else {
//                 System.out.println("❌ Token inválido o usuario no encontrado");
//             }
//         } else if (username == null) {
//             System.out.println("⚠️ No se encontró username en el token");
//         } else {
//             System.out.println("⚠️ Authentication ya estaba establecida");
//         }

//         // 🔹 Continuar con la cadena de filtros
//         filterChain.doFilter(request, response);
//     }
// }
// package com.david.tfg.confing;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.core.annotation.Order;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.david.tfg.entities.User;
// import com.david.tfg.services.UserService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// @Order(1) // Después de CORS
// public class JwtFilter extends OncePerRequestFilter {

//     private final JwtUtil jwtUtil;
//     private final UserService userService;

//     public JwtFilter(JwtUtil jwtUtil, UserService userService) {
//         this.jwtUtil = jwtUtil;
//         this.userService = userService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//                                     throws ServletException, IOException {
//         if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String path = request.getRequestURI();
//         if (path.startsWith("/img/") || path.startsWith("/css/") || path.startsWith("/js/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         final String authHeader = request.getHeader("Authorization");
//         String username = null;
//         String token = null;

//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             token = authHeader.substring(7);
//             try {
//                 username = jwtUtil.extractUsername(token);
//             } catch (Exception e) {
//                 System.out.println("⚠️ Error al extraer username del token: " + e.getMessage());
//             }
//         }

//         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             User usuario = userService.findByNombreUsuario(username);

//             if (usuario != null && jwtUtil.validateToken(token, username)) {
//                 String rol = jwtUtil.extractRole(token);

//                 UsernamePasswordAuthenticationToken authToken =
//                         new UsernamePasswordAuthenticationToken(
//                                 usuario,
//                                 null,
//                                 List.of(new SimpleGrantedAuthority("ROLE_" + rol))
//                         );

//                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(authToken);
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }
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

    public JwtFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                String username = jwtUtil.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    User user = userService.findByNombreUsuario(username);

                    if (user != null && jwtUtil.validateToken(token, username)) {

                        String rol = jwtUtil.extractRole(token);
                        SimpleGrantedAuthority authority =
                                new SimpleGrantedAuthority("ROLE_" + rol);

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        user,
                                        null,
                                        List.of(authority)
                                );

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error en JWT: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
