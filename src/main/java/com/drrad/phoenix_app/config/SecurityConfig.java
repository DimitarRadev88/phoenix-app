package com.drrad.phoenix_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in API applications,
                // but keep it enabled for browser-based apps.
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**", "static/**").permitAll() // Allow public access to /public/**
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Require ADMIN role for /admin/**
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Require USER or ADMIN role for /user/**
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(withDefaults()) // Enable form-based login with default settings
                .httpBasic(withDefaults()); // Enable HTTP Basic authentication with default settings

        return http.build();
    }
}
