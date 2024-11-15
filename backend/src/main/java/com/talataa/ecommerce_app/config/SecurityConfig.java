package com.talataa.ecommerce_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll() // Permite POST a /auth
                        .requestMatchers(HttpMethod.POST, "/users**").permitAll() // Permite acceso a swagger-ui
                        .requestMatchers(HttpMethod.POST, "/customers").permitAll() // Permite acceso a la documentación de API
                        .requestMatchers(HttpMethod.POST, "/admins").permitAll() // Permite acceso a swagger-ui.html
                        .requestMatchers(HttpMethod.GET, "/doctors/**").hasRole("ADMIN") // Solo para doctores
                        .requestMatchers(HttpMethod.GET, "/patients/**").hasRole("CUS") // Solo para pacientes
                        .anyRequest().authenticated() // Cualquier otra solicitud necesita autenticación
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Añadir el filtro JWT antes del filtro de autenticación

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
