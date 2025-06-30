package com.comercio.promociones.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Habilita CORS
            .and()
            .csrf().disable() // Deshabilita CSRF para APIs REST
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/promociones/**").permitAll() // Permitir acceso público a /api/promociones
                .anyRequest().authenticated() // Resto requiere autenticación
            )
            .httpBasic(); // Autenticación básica (opcional)

        return http.build();
    }
}