package com.evertec.tienda_online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // Seguridad de rutas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // útil para Postman, pero en producción deberías usarlo con cuidado
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").authenticated() // protege tus APIs
                .anyRequest().permitAll()
            )
            .httpBasic(); // habilita autenticación básica

        return http.build();
    }

    // Usuario en memoria
    @Bean
    public UserDetailsService users() {
        UserDetails user = User
                .withUsername("user")
                .password("admin123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // Permitir contraseñas sin encriptar (para pruebas)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
