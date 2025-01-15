package com.example.SpringEnvioMensajes.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Clase que representa la entidad Rol en la base de datos.
 */
@Entity
// Marca la clase como una entidad JPA.
@Table(name = "rol")
@Data
// Define el nombre de la tabla en la base de datos asociada a esta entidad.
public class Rol {
    
    @Id // Indica que el atributo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de la clave primaria
    private int idRol; // Atributo que representa el id del rol
    
    @Column(nullable = false, unique = true, length = 150) // Indica que el atributo no puede ser nulo, es único y tiene una longitud máxima de 150 caracteres
    private String nombreRol; // Atributo que representa el nombre del rol
}
