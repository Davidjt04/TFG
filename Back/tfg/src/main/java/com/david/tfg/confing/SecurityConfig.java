package com.david.tfg.confing;

// import org.springframework.context.annotation.Bean;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {
//     //Filtros para la seguridad de las peticiones HTTP
// //  @Bean
// //     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// //         http
// //         .cors(cors -> {})
// //         //Desactiva la protección CSRF (Cross-Site Request Forgery)
// //             .csrf().disable()
// //             //Configura las reglas de autorización 
// //             .authorizeHttpRequests(auth -> auth
// //             // Permite el acceso sin autenticación a las rutas que empiecen por /auth/
// //                 .requestMatchers("/auth/**").permitAll()
// //                 //todas las demas rutas requieren autenticacion 
// //                 .anyRequest().authenticated()
// //             )
// //             .httpBasic(); //TODO O usa .formLogin() para login por formulario
// //         return http.build();
// //     }

    
//     private final JwtFilter jwtFilter;

//     // Inyectamos el filtro JWT
//     public SecurityConfig(JwtFilter jwtFilter) {
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             // Habilita CORS y desactiva CSRF (no necesario con JWT)
//             .cors(cors -> {})
//             .csrf(csrf -> csrf.disable())

//             // Autorización de rutas
//             .authorizeHttpRequests(auth -> auth
//                 // Rutas públicas
//                 .requestMatchers("/**","/img/**","/service/**","/detallesTrabajador/**").permitAll()

//                 // .requestMatchers("/auth/**").permitAll()
//                 // Endpoints accesibles solo por ADMIN
// //                 .requestMatchers("/ADMIN/**").hasRole("ADMIN")
// // //             //.requestMatchers("/partido/lista", "/arbitro/lista", "/evento/guardar").hasAnyAuthority("READ", "WRITE")

// //                 // // Endpoints accesibles por el CLIENTE
// //                 .requestMatchers("/CLIENTE/cita/servicios","/CLIENTE/cita/info","/CLIENTE/cita/imagenes",
// //                 "/CLIENTE/cita/imagenes","/CLIENTE/tienda","/CLIENTE/tienda/saldo","monedero","carrito","resenias").hasAnyRole("CLIENTE", "ADMIN")
// //                 // // Endpoints accesibles por el TRABAJADOR
// //                 .requestMatchers("/TRABAJOR/calendario","lading","monedero","carrito","resenias").hasAnyRole("TRAJADOR")

//                 // Rutas protegidas (requieren token JWT válido)
//                 .anyRequest().authenticated()
//             )
 
//             // No usamos sesiones en el servidor (stateless, solo tokens)
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//             // Añadimos nuestro filtro JWT antes del filtro por defecto de Spring Security
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // Necesario para manejar autenticación (aunque no uses el manager directamente)
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//         return authConfig.getAuthenticationManager();
//     }
// }

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Habilita CORS (usa ConfingCors)
            .cors(cors -> {})

            // CSRF desactivado
            .csrf(csrf -> csrf.disable())

            // Reglas de autorización
            .authorizeHttpRequests(auth -> auth
            // Rutas públicas
            .requestMatchers("/", "/login", "/registro", "/review").permitAll()
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/auth/**").permitAll()

            // Rutas por rol
            .requestMatchers("/ADMIN/**").hasRole("ADMIN")
            .requestMatchers("/CLIENTE/**").hasAnyRole("CLIENTE", "ADMIN")
            .requestMatchers("/TRABAJADOR/**").hasAnyRole("TRABAJADOR", "ADMIN")

            // Todo lo demás requiere autenticación
            .anyRequest().authenticated()
        )


            // Stateless: no se usan sesiones
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Añade el filtro JWT
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // AuthenticationManager necesario para login si se usa Spring Security
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

