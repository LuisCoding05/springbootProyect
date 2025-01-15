package com.example.SpringEnvioMensajes.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Clase que representa la entidad Usuario en la base de datos.
 */
@Entity
// Marca la clase como una entidad JPA.
@Table(name = "usuario")
@Data
// Define el nombre de la tabla en la base de datos asociada a esta entidad.
public class Usuario {
    
    @Id // Indica que el atributo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de la clave primaria
    private int idUsuario; // Atributo que representa el id del usuario

    @Column(nullable = false, unique = true, length = 150) // Indica que el atributo no puede ser nulo, es único y tiene una longitud máxima de 150 caracteres
    private String nombreUsuario; // Atributo que representa el nombre del usuario

    @Column(nullable = false, length = 100) // Indica que el atributo no puede ser nulo y tiene una longitud máxima de 100 caracteres
    private String contrasenaUsuario; // Atributo que representa la contraseña del usuario

    @ManyToOne(fetch = FetchType.EAGER) // Establece la relación con la entidad Rol, cargando los datos de forma inmediata
    @JoinColumn(name = "rolUsuario", nullable = false) // Define la columna de unión y que no puede ser nula
    private Rol rol; // Atributo que representa el rol del usuario
}
