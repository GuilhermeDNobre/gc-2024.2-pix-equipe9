package com.ufc.pix.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SecurityFilter securityFilter;

    private final String[] allowedForAdmin = {
            "/users/{userId}",
            "/pix/**",
            "/generate-reports/**"
    };
    private final String[] freeRoutes = {//List of free routes
            "/h2/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api-docs/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp.policyDirectives(
                                "default-src 'self'; " +
                                        "script-src 'self' 'unsafe-inline'; " +
                                        "style-src 'self' 'unsafe-inline'; " +
                                        "frame-ancestors 'self'")) // Permite scripts e estilos inline
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(freeRoutes).permitAll()

                        .requestMatchers(HttpMethod.DELETE,"users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "users").permitAll()
                        .requestMatchers(HttpMethod.POST, "users/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"users/**").hasRole("ADMIN")

                        .requestMatchers("account/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "account/**").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //adiciona filtro antes
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}