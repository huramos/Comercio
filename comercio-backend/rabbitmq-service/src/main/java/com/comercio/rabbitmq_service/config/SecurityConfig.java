package com.comercio.rabbitmq_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and() // Habilita CORS
            .csrf().disable() // Deshabilita CSRF para APIs REST
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/public/**").permitAll() // Endpoints públicos
                .anyRequest().authenticated() // Resto requiere autenticación
            )
            .httpBasic(); // Autenticación básica (puedes cambiar a JWT si lo prefieres)

        return http.build();
    }
}