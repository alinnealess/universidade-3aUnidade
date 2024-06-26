package com.imd.universidade.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/alunos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/professores/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/turmas/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/alunos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/professores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/turmas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/alunos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/professores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,  "/turmas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/alunos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/professores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/turmas/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}