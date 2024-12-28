package com.example.SpringEnvioMensajes.service;

import com.example.SpringEnvioMensajes.model.Usuario;
import com.example.SpringEnvioMensajes.repository.IUserDao;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserDao usuarioRepository;

    public CustomUserDetailsService(IUserDao usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
                // Imprime la contraseña cifrada en los logs
                log.info("Contraseña del usuario '{}': {}", usuario.getNombreUsuario(), usuario.getContrasenaUsuario());
        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContrasenaUsuario())
                .roles(usuario.getRol().getNombreRol().replace("ROLE_", "")) // Quita el prefijo "ROLE_" si es necesario
                .build();
    }
}

