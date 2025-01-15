package com.example.SpringEnvioMensajes.service;

import com.example.SpringEnvioMensajes.model.Rol;
import com.example.SpringEnvioMensajes.model.Usuario;
import com.example.SpringEnvioMensajes.repository.IRolDao;
import com.example.SpringEnvioMensajes.repository.IUserDao;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserDao usuarioRepository;

    @Autowired
    private IRolDao rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(IUserDao usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContrasenaUsuario())
                .roles(usuario.getRol().getNombreRol().replace("ROLE_", ""))
                .build();
    }

    public void guardar(Usuario usuario) {
        // Verificar si el usuario ya existe en la base de datos
        if (usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe: " + usuario.getNombreUsuario());
        }
    
    Rol rol = rolRepository.findByNombreRol(usuario.getRol().getNombreRol());
    if (rol == null) {
        rol = new Rol();
        rol.setNombreRol(usuario.getRol().getNombreRol());
        rol = rolRepository.save(rol);
    }
    
    usuario.setRol(rol);
    usuario.setContrasenaUsuario(passwordEncoder.encode(usuario.getContrasenaUsuario()));
    usuarioRepository.save(usuario);
    }
}