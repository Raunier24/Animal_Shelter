package com.project.Animal_Shelter.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.Animal_Shelter.security.jwt.JwtAuthTokenFilter;
import com.project.Animal_Shelter.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthTokenFilter jwtAuthTokenFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthTokenFilter jwtAuthTokenFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthTokenFilter = jwtAuthTokenFilter;
    }

    // Configuración del PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración del SecurityFilterChain en lugar de WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Permitir rutas de autenticación
                        .requestMatchers("/api/donations/**").hasRole("ADMIN") // Requerir el rol de ADMIN para estas rutas
                        .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin estado
                .addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class); // Filtro JWT

        return http.build();
    }

    // Para proporcionar el AuthenticationManager (opcional si es necesario para autenticación manual)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}