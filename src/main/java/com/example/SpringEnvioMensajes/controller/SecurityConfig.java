package com.example.SpringEnvioMensajes.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration // Indica que esta clase es una clase de configuración
public class SecurityConfig {

    // Configura la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/anexar", "/guardar", "/editar/**", "/borrar/**").hasRole("ADMIN")
                .requestMatchers("/error/**").permitAll() // Permite el acceso a las páginas de error sin autenticación
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
            )
            .formLogin(form -> form
                .loginPage("/registro") // Página personalizada de inicio de sesión
                .defaultSuccessUrl("/", true) // Página a la que redirige después de iniciar sesión
                .permitAll() // Permite acceso a todos a la página de inicio de sesión
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/registro?logout")
                .permitAll()
            )
            .exceptionHandling(exceptions -> exceptions
                .accessDeniedPage("/error/403") // Página para el error 403
                .authenticationEntryPoint((request, response, authException) -> {
                    // Registrar información en los logs
                    log.info("Intento de acceso no autenticado desde: {}", request.getRequestURL());
                    log.error("Error de autenticación: {}", authException.getMessage());
    
                    // Redirigir a la página de inicio de sesión si no está autenticado
                    response.sendRedirect("/registro"); // Redirige a la página de login
                })
            );
            
        return http.build();
    }
    

    // Configura el servicio de detalles de usuario en memoria
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Crea un usuario con rol ADMIN y USER
        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("159")) // Contraseña codificada
            .roles("ADMIN", "USER") // Roles asignados
            .build();

        // Crea un usuario con rol USER
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode("753")) // Contraseña codificada
            .roles("USER") // Rol asignado
            .build();

        // Retorna un InMemoryUserDetailsManager con los usuarios creados
        return new InMemoryUserDetailsManager(admin, user);
    }

    // Configura el codificador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliza BCrypt para codificar las contraseñas
    }

    // Configura el administrador de autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // Retorna el AuthenticationManager configurado
    }

    // Configura personalizaciones de seguridad web
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**"); // Ignora las rutas especificadas para la seguridad web
    }
}