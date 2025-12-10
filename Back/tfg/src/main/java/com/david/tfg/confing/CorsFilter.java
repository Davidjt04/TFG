// package com.david.tfg.confing;

// import java.io.IOException;

// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// @Order(0) // Se ejecuta primero
// public class CorsFilter extends OncePerRequestFilter {

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//         response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//         response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
//         response.setHeader("Access-Control-Allow-Credentials", "true");

//         // Permitir OPTIONS sin pasar por JWT
//         if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//             response.setStatus(HttpServletResponse.SC_OK);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
