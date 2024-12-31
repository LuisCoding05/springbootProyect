package com.example.controladorrest.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST
public class UsuarioController { // Clase controlador
    @GetMapping("/usuarios") // Indica que este método se ejecutará cuando se haga una petición GET a /usuarios
    public List<Usuario> obtenerUsuarios() { // Método que retorna una lista de usuarios
        Usuario Juan = new Usuario(1, "El pepe", 25); // Crea un usuario
        Usuario Maria = new Usuario(2, "Maria", 30); // Crea otro usuario
        Usuario Pedro = new Usuario(3, "Pedro", 35); // Crea otro usuario
        return List.of(Juan, Maria, Pedro); // Retorna una lista de usuarios
    }
}
 
@Data // Anotación de Lombok para generar automáticamente los getters y setters
class Usuario { // Clase Usuario
    private int id; // Atributo id
    private String nombre; // Atributo nombre
    private int edad; // Atributo edad

    // Constructor
    public Usuario(int id, String nombre, int edad) { // Constructor de la clase Usuario
        this.id = id; // Inicializa el atributo id
        this.nombre = nombre; // Inicializa el atributo nombre
        this.edad = edad; // Inicializa el atributo edad
    }
}
