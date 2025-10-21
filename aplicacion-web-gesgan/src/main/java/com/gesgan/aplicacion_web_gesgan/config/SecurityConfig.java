package com.gesgan.aplicacion_web_gesgan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gesgan.aplicacion_web_gesgan.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // 🔓 Páginas públicas - acceso sin autenticación
                .requestMatchers(
                    "/",
                    "/inicio", 
                    "/login", 
                    "/registro",
                    "/css/**", 
                    "/js/**", 
                    "/images/**",
                    "/webjars/**",
                    "/favicon.ico",
                    "/error"
                ).permitAll()
                
                // 🔐 Páginas que requieren autenticación
                .requestMatchers(
                    "/ganado",
                    "/ganado/**",
                    "/sanidad", 
                    "/sanidad/**",
                    "/config",
                    "/config/**"
                ).authenticated()
                
                // 🔐 APIs que requieren autenticación
                .requestMatchers(
                    "/api/**"
                ).authenticated()
                
                // 🛡️ Todas las demás rutas requieren autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")                    // Página personalizada de login
                .loginProcessingUrl("/login")           // URL que procesa el login
                .defaultSuccessUrl("/inicio?login=true", true) // Redirigir después de login exitoso
                .failureUrl("/login?error=true")        // Redirigir después de login fallido
                .usernameParameter("username")          // Parámetro del formulario para username
                .passwordParameter("password")          // Parámetro del formulario para password
                .permitAll()                            // Permitir acceso a todos al formulario de login
            )

            .exceptionHandling(exception -> exception
                .accessDeniedPage("/login?access_denied=true") // Página para acceso denegado
            )
            .sessionManagement(session -> session
                .maximumSessions(1)                     // Máximo 1 sesión por usuario
                .maxSessionsPreventsLogin(false)        // Permitir nuevo login (cierra sesión anterior)
            )
            .authenticationProvider(authenticationProvider())
            .csrf(csrf -> csrf.disable()); // ⚠️ Deshabilitar CSRF temporalmente para desarrollo

        return http.build();
    }
}