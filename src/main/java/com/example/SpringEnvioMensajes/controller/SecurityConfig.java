package com.example.SpringEnvioMensajes.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.SpringEnvioMensajes.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .userDetailsService(userDetailsService)
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/anexar", "/guardar", "/editar/**", "/borrar/**").hasRole("ADMIN") //Estas rutas solo pueden ser accedidas por usuarios con rol ADMIN
                .requestMatchers("/error/**", "/login", "/register").permitAll() //Estas rutas son accesibles por cualquier usuario
                .anyRequest().authenticated() //Cualquier otra ruta requiere autenticación
            )
            .formLogin(form -> form
                .loginPage("/login") //Ruta de la página de inicio de sesión personalizada, con nombre cambiado a "registro"
                .defaultSuccessUrl("/", true) //Ruta de redirección después de iniciar sesión
                .permitAll() //Permitir acceso a la página de inicio de sesión sin autenticación
            )
            .logout(logout -> logout
                .logoutUrl("/logout") //Ruta de la página de cierre de sesión 
                .logoutSuccessUrl("/login?logout") //Ruta de redirección después de cerrar sesión
                .permitAll() //Permitir acceso a la página de cierre de sesión sin autenticación
            )
            .exceptionHandling(exceptions -> exceptions
                .accessDeniedPage("/error/403") //Ruta de la página de error 403
                .authenticationEntryPoint((request, response, authException) -> { //Manejo de errores de autenticación
                    log.info("Intento de acceso no autenticado desde: {}", request.getRequestURL()); //Registro de intento de acceso no autenticado
                    log.error("Error de autenticación: {}", authException.getMessage()); //Registro de error de autenticación
                    response.sendRedirect("/login?error=true"); //Redirección a la página de inicio de sesión con parámetro de error
                })
            );

        return http.build(); //Construcción de la configuración de seguridad
    }

    public PasswordEncoder passwordEncoder() { //Bean para encriptar contraseñas
        return new BCryptPasswordEncoder(); //Encriptación de contraseñas con BCrypt
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { //Bean para la configuración de autenticación
        return authenticationConfiguration.getAuthenticationManager(); //Obtención de la configuración de autenticación
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { //Bean para la configuración de seguridad web
        return (web) -> web.ignoring() //Ignorar las siguientes rutas
            .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**"); //Rutas de recursos estáticos
    }
}