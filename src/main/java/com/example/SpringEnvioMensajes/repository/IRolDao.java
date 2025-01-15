package com.example.SpringEnvioMensajes.repository;

import com.example.SpringEnvioMensajes.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolDao extends JpaRepository<Rol, Integer> {
    Rol findByNombreRol(String nombreRol);
}