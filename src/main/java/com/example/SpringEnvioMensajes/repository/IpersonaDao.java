package com.example.SpringEnvioMensajes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringEnvioMensajes.model.Persona;

// Interfaz que extiende de CrudRepository para realizar operaciones CRUD en la base de datos.
@Repository
// Marca la clase como un componente de acceso a datos.
public interface IpersonaDao extends CrudRepository<Persona, Long> {
    // Método que devuelve la primera persona en la base de datos.
    public Persona findFirstByOrderByIdAsc();
}