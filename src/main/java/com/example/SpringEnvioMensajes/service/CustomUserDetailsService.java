package com.example.SpringEnvioMensajes.service;

import com.example.SpringEnvioMensajes.model.Usuario;
import com.example.SpringEnvioMensajes.repository.IUserDao;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// Clase que implementa la interfaz UserDetailsService de Spring Security para cargar los datos del usuario.
@Slf4j
// Marca la clase como un componente de servicio.
@Service
/**
 * Clase que implementa la interfaz UserDetailsService de Spring Security para cargar los datos del usuario.
 */
public class CustomUserDetailsService implements UserDetailsService { // Implementa la interfaz UserDetailsService de Spring Security.

    private final IUserDao usuarioRepository; // Inyecta la interfaz IUserDao en el servicio.

    public CustomUserDetailsService(IUserDao usuarioRepository)  { // Constructor del servicio.
        this.usuarioRepository = usuarioRepository; // Inicializa el repositorio de usuarios.
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Método que carga los datos del usuario por su nombre de usuario.
        Usuario usuario = usuarioRepository.findByNombreUsuario(username) // Busca un usuario por su nombre de usuario.
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username)); // Lanza una excepción si el usuario no se encuentra.
                // Imprime la contraseña cifrada en los logs
        return User.builder() // Devuelve un objeto UserDetails con los datos del usuario.
                .username(usuario.getNombreUsuario()) // Establece el nombre de usuario.
                .password(usuario.getContrasenaUsuario()) // Establece la contraseña del usuario.
                .roles(usuario.getRol().getNombreRol().replace("ROLE_", "")) // Quita el prefijo "ROLE_" si es necesario
                .build(); // Construye el objeto UserDetails.
    }
}

