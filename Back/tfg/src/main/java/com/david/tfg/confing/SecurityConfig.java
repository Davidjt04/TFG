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
            .requestMatchers("/").permitAll()

            .requestMatchers("/", "/login", "/registro", "/review").permitAll()
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/auth/**").permitAll()
            // .requestMatchers("/workerschedule/usuario/id").hasAnyRole("TRABAJADOR","ADMIN")
            // .requestMatchers("/workerschedule/**").permitAll()
            .requestMatchers("/CartHasArticle/**").permitAll()
            .requestMatchers("/carrito/**").hasAnyRole("CLIENTE", "ADMIN")


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





// package com.david.tfg.confing;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
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
//             // Habilita CORS
//             .cors(cors -> {})
//             // Desactiva CSRF
//             .csrf(csrf -> csrf.disable())
//             // Reglas de autorización
//             .authorizeHttpRequests(auth -> auth
//                 // Todas las rutas permitidas (sin autenticación)
//                 .requestMatchers("/**").permitAll()
//             )
//             // Stateless: no usamos sesiones
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             // Añade el filtro JWT (aunque ahora no haga nada)
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//         return authConfig.getAuthenticationManager();
//     }
// }


