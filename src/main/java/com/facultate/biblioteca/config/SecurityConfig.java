package com.facultate.biblioteca.config;

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
                .csrf(csrf -> csrf.disable()) // Dezactivăm CSRF pentru testare
                .authorizeHttpRequests(auth -> auth
                        // Am adăugat aici "/swagger-ui/**" și "/v3/api-docs/**"
                        .requestMatchers("/books", "/books/**", "/api/**", "/login", "/css/**", "/js/**", "/swagger-ui/**", "/v3/api-docs/**",
                                "/author/**", "/author/*", "/author",
                                "/category/**", "/category/*", "/category",
                                "/loan/**", "/loan/*", "/loan",
                                "/role/**", "/role/*", "/role",
                                "/userprofile/**", "/userprofile/*", "/userprofile",
                                "/user/**", "/user/*", "/user").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/books", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
