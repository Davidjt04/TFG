// package com.david.tfg.confing;

// // import org.springframework.context.annotation.Bean;



// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .cors(cors -> {})
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 // Endpoints públicos
//                 .requestMatchers("/", "/login", "/registro", "/review", "/auth/**","/img/**").permitAll()
//                 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                
//                 // Endpoints de cliente
//                 .requestMatchers("/CLIENTE/**").hasAnyRole("CLIENTE", "ADMIN")
//                 .requestMatchers("/ADMIN/**").hasRole("ADMIN")
//                 .requestMatchers("/TRABAJADOR/**").hasAnyRole("TRABAJADOR", "ADMIN")

//                 // Todo lo demás requiere autenticación
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//         return authConfig.getAuthenticationManager();
//     }
// }
// package com.david.tfg.confing;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     /**
//      * Ignora por completo los recursos estáticos como /img/** para que no pasen por Spring Security.
//      */
//     @Bean
//     public WebSecurityCustomizer webSecurityCustomizer() {
//         return (web) -> web.ignoring().requestMatchers("/img/**", "/css/**", "/js/**");
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .cors(cors -> {}) // habilita CORS
//             .csrf(csrf -> csrf.disable()) // deshabilita CSRF para API REST
//             .authorizeHttpRequests(auth -> auth
//                 // Endpoints públicos
//                 .requestMatchers("/", "/login", "/registro", "/review", "/auth/**").permitAll()
//                 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

//                 .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/horas").hasAnyRole("CLIENTE", "TRABAJADOR")
//                 .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/trabajadores").hasAnyRole("CLIENTE", "TRABAJADOR")
//                 .requestMatchers(HttpMethod.POST, "/TRABAJADOR/HORARIO/marcar-no-disponible").hasRole("CLIENTE")


//                 // Endpoints de cliente
//                 .requestMatchers("/CLIENTE/**").hasAnyRole("CLIENTE", "ADMIN")
//                 .requestMatchers("/ADMIN/**").hasRole("ADMIN")
//                 .requestMatchers("/TRABAJADOR/**").hasAnyRole("TRABAJADOR", "ADMIN")

//                 // Todo lo demás requiere autenticación
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             // JWT filter para endpoints protegidos
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//         return authConfig.getAuthenticationManager();
//     }
// }
// package com.david.tfg.confing;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;
//     private final CorsFilter corsFilter; // <- nuestro filtro de CORS global

//     public SecurityConfig(JwtFilter jwtFilter, CorsFilter corsFilter) {
//         this.jwtFilter = jwtFilter;
//         this.corsFilter = corsFilter;
//     }

//     /**
//      * Ignora recursos estáticos (img, css, js)
//      */
//     @Bean
//     public WebSecurityCustomizer webSecurityCustomizer() {
//         return (web) -> web.ignoring().requestMatchers("/img/**", "/css/**", "/js/**");
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable()) // API REST, no CSRF
//             .authorizeHttpRequests(auth -> auth
//                 // Endpoints públicos
//                 .requestMatchers("/", "/login", "/registro", "/review", "/auth/**").permitAll()
//                 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // permitir preflight CORS

//                 // Ejemplos de roles
//                 .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/horas").hasAnyRole("CLIENTE", "TRABAJADOR")
//                 .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/trabajadores").hasAnyRole("CLIENTE", "TRABAJADOR")
//                 .requestMatchers(HttpMethod.POST, "/TRABAJADOR/HORARIO/marcar-no-disponible").hasRole("CLIENTE")

//                 // Endpoints por rol
//                 .requestMatchers("/CLIENTE/**").hasAnyRole("CLIENTE", "ADMIN")
//                 .requestMatchers("/ADMIN/**").hasRole("ADMIN")
//                 .requestMatchers("/TRABAJADOR/**").hasAnyRole("TRABAJADOR", "ADMIN")

//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//         // 🔹 Añadir filtros
//         http.addFilterBefore(corsFilter, JwtFilter.class); // primero CORS
//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // luego JWT

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//         return authConfig.getAuthenticationManager();
//     }
// // }
// package com.david.tfg.confing;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     // Ignorar recursos estáticos
//     @Bean
//     public WebSecurityCustomizer webSecurityCustomizer() {
//         return (web) -> web.ignoring().requestMatchers("/img/**", "/css/**", "/js/**");
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable()) // API REST, no CSRF
//             .authorizeHttpRequests(auth -> auth
//                 // Endpoints públicos
//                 .requestMatchers("/", "/login", "/registro", "/review", "/auth/**").permitAll()
//                 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Preflight CORS
//                 .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/horas").hasAnyRole("CLIENTE", "TRABAJADOR")
//                 .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/trabajadores").hasAnyRole("CLIENTE", "TRABAJADOR")
//                 .requestMatchers(HttpMethod.POST, "/TRABAJADOR/HORARIO/marcar-no-disponible").hasRole("CLIENTE")
//                 .requestMatchers(HttpMethod.DELETE, "/articulo/borrar/**").hasRole("ADMIN")

//                 // Endpoints por rol
//                 .requestMatchers("/CLIENTE/**").hasAnyRole("CLIENTE", "ADMIN")
//                 .requestMatchers("/ADMIN/**").hasRole("ADMIN")
//                 .requestMatchers("/TRABAJADOR/**").hasAnyRole("TRABAJADOR", "ADMIN")

//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//         // JWT filter (CORS ya se ejecuta primero gracias a @Order en el filtro)
//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//         return authConfig.getAuthenticationManager();
//     }
// }

// package com.david.tfg.confing;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public WebSecurityCustomizer webSecurityCustomizer() {
//         return (web) -> web.ignoring().requestMatchers("/img/**", "/css/**", "/js/**");
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//         http
//             .cors(cors -> cors.disable())      // ❗ LO QUITAMOS, YA SE GESTIONA EN CorsFilter
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/", "/login", "/registro", "/review", "/auth/**").permitAll()
//                 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

//                 .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/horas").hasAnyRole("CLIENTE", "TRABAJADOR")
//                 .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/trabajadores").hasAnyRole("CLIENTE", "TRABAJADOR")
//                 .requestMatchers(HttpMethod.POST, "/TRABAJADOR/HORARIO/marcar-no-disponible").hasRole("TRABAJADOR")
//                 .requestMatchers(HttpMethod.DELETE, "/articulo/borrar/**").hasRole("ADMIN")

//                 .requestMatchers("/CLIENTE/**").hasAnyRole("CLIENTE", "ADMIN")
//                 .requestMatchers("/ADMIN/**").hasRole("ADMIN")
//                 .requestMatchers("/TRABAJADOR/**").hasAnyRole("TRABAJADOR", "ADMIN")

//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session ->
//                     session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             );

//         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }



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
                .requestMatchers(HttpMethod.POST, "/TRABAJADOR/HORARIO/marcar-no-disponible")
                    .hasAnyRole("TRABAJADOR", "CLIENTE")
                    
                .requestMatchers(HttpMethod.DELETE, "/articulo/borrar/**").hasRole("ADMIN")

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
