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
package com.david.tfg.confing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Ignora por completo los recursos estáticos como /img/** para que no pasen por Spring Security.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/img/**", "/css/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> {}) // habilita CORS
            .csrf(csrf -> csrf.disable()) // deshabilita CSRF para API REST
            .authorizeHttpRequests(auth -> auth
                // Endpoints públicos
                .requestMatchers("/", "/login", "/registro", "/review", "/auth/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/horas").hasAnyRole("CLIENTE", "TRABAJADOR")
                .requestMatchers(HttpMethod.GET, "/TRABAJADOR/HORARIO/trabajadores").hasAnyRole("CLIENTE", "TRABAJADOR")
                .requestMatchers(HttpMethod.POST, "/TRABAJADOR/HORARIO/marcar-no-disponible").hasRole("CLIENTE")


                // Endpoints de cliente
                .requestMatchers("/CLIENTE/**").hasAnyRole("CLIENTE", "ADMIN")
                .requestMatchers("/ADMIN/**").hasRole("ADMIN")
                .requestMatchers("/TRABAJADOR/**").hasAnyRole("TRABAJADOR", "ADMIN")

                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // JWT filter para endpoints protegidos
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}



