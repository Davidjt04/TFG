


package com.david.tfg.confing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // Ignorar recursos estáticos
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/img/**", "/css/**", "/js/**");
    }

    // Manejo de errores de autenticación
    @Bean
    public AuthenticationEntryPoint unauthorizedHandler() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {}) // Deja que el CorsFilter maneje todo
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler()))
            .authorizeHttpRequests(auth -> auth

                // Endpoints públicos
                .requestMatchers("/", "/login", "/registro", "/review", "/auth/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Tus reglas específicas
                .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/horas").hasAnyRole("CLIENTE", "TRABAJADOR")
                .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/trabajadores").hasAnyRole("CLIENTE", "TRABAJADOR")
                
                // ✅ Aquí añadimos que también CLIENTE pueda acceder
                .requestMatchers(HttpMethod.POST, "/TRABAJADOR/HORARIO/marcar-no-disponible", "/TRABAJADOR/HORARIO/marcar-disponible")
                    .hasAnyRole("TRABAJADOR", "CLIENTE")
                    .requestMatchers(HttpMethod.GET, "/detallesTrabajador/lista")
                    .hasAnyRole("CLIENTE", "TRABAJADOR", "ADMIN")
                    
                .requestMatchers(HttpMethod.DELETE, "/articulo/borrar/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/cita/borrar/**").hasRole("CLIENTE")


                // Rutas por rol
                .requestMatchers("/CLIENTE/**").hasAnyRole("CLIENTE", "ADMIN")
                .requestMatchers("/ADMIN/**").hasRole("ADMIN")
                .requestMatchers("/TRABAJADOR/**").hasAnyRole("TRABAJADOR", "ADMIN")

                .anyRequest().authenticated()
            )
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
