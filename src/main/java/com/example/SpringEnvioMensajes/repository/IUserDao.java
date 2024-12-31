package com.example.SpringEnvioMensajes.repository;

import com.example.SpringEnvioMensajes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Interfaz que extiende de JpaRepository para realizar operaciones CRUD en la base de datos.
@Repository // Marca la clase como un componente de acceso a datos.
public interface IUserDao extends JpaRepository<Usuario, Integer> { // Extiende de JpaRepository con la entidad Usuario y el tipo de dato Integer.
    Optional<Usuario> findByNombreUsuario(String nombreUsuario); // Método que busca un usuario por su nombre de usuario.
}
