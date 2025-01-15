package com.example.SpringEnvioMensajes.model; 
// Define el paquete donde se encuentra la clase, útil para organizar el proyecto y evitar conflictos de nombres.

// Importación de Serializable para permitir que la clase pueda ser serializada.
import java.io.Serializable;

// Marca la clase como una entidad JPA que representa una tabla en la base de datos.
import jakarta.persistence.Entity; 

// Especifica la estrategia para generar valores únicos para la clave primaria.
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 

// Indica que el atributo anotado es la clave primaria de la tabla.
import jakarta.persistence.Id; 

// Marca la clase como vinculada a una tabla específica en la base de datos.
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// Anotación de Lombok para generar automáticamente métodos como getter, setter, equals, hashCode y toString.
import lombok.Data; 

// Clase de entidad que representa la tabla "persona" en la base de datos.
@Data // Lombok genera automáticamente los métodos estándar para esta clase.
@Entity // Marca la clase como una entidad JPA.
@Table(name = "persona") // Define el nombre de la tabla en la base de datos asociada a esta entidad.
public class Persona implements Serializable { 
    // Implementa Serializable para permitir que la clase se convierta en un flujo de bytes, necesario para algunas operaciones como almacenamiento en caché o envío por red.

    private static final long serialVersionUID = 1L; 
    // Define un identificador único para la clase, útil durante la serialización para garantizar la compatibilidad.

    @Id 
    // Marca este campo como la clave primaria de la tabla.

    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // Especifica que los valores de la clave primaria serán generados automáticamente por la base de datos (estrategia de autoincremento).

    private Long id; 
    // Atributo que representa la columna "id" de la tabla, utilizada como clave primaria.
    @NotBlank // Valida que el campo no sea nulo ni vacío.
    private String nombre; 
    // Atributo que representa la columna "nombre" de la tabla, para almacenar el nombre de la persona.
    @NotBlank
    private String apellido; 
    // Atributo que representa la columna "apellido", para almacenar el apellido de la persona.
    @NotBlank
    private String telefono; 
    // Atributo que representa la columna "telefono", para almacenar el número de teléfono de la persona.
    @NotBlank
    @Email
    private String email; 
    // Atributo que representa la columna "email", para almacenar el correo electrónico de la persona.
    @NotNull
    @Min(0)
    private Integer edad; 
    // Atributo que representa la columna "edad", para almacenar la edad de la persona.
}
