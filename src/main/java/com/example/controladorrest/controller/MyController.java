package com.example.controladorrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Indica que esta clase es un controlador
public class MyController { // Clase controlador

    @GetMapping("/index") // Indica que este método se ejecutará cuando se haga una petición GET a /index
    public String index() { // Método que retorna un String
        return "index"; // Retorna el nombre de la vista
    }
}
