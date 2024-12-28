package com.example.SpringEnvioMensajes.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
// Controlador que maneja las solicitudes web relacionadas con los usuarios.
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SpringEnvioMensajes.model.Persona;
import com.example.SpringEnvioMensajes.service.PersonaServicio;


@Controller
//Interfaz de información de registros
@Slf4j
public class UserController {

    @Autowired
    // Inyecta la interfaz PersonaServicio en el controlador
    private PersonaServicio personaServicio;
    //Agrega al directorio raíz la función index
    @GetMapping("/")
    public String index(Model model) {
        // Consulta una lista de personas desde la base de datos
        List<Persona> personas = personaServicio.listar();

        // Agrega los datos al modelo
        model.addAttribute("personas", personas);

        //Informa que se está ejecutando el controlador
        log.info("Ejecutando indice");
        // Devuelve la vista index.html
        return "index";
    }

    @GetMapping("/anexar")
    public String anexar(Persona persona) {
        log.info("Ejecutando página agregar persona");
        return "cambiar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors error) {
        if (error.hasErrors()) {
            return "cambiar";
        }
        personaServicio.guardar(persona);
        log.info("Persona guardada");
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(Persona persona, Model model) {
        persona = personaServicio.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        log.info("Ejecutando página editar persona");
        return "cambiar";
    }

    
    //Opcion 1 
    /* 
    @GetMapping("/borrar/{id}")
    public String borrar(Persona persona, Model model) {
        personaServicio.eliminar(persona);
        log.info("Ejecutada acción para eliminar persona");
        return "redirect:/";
    }
    */

    //Opcion 2
    
    @GetMapping("/borrar")
    public String borrar(Persona persona, Model model) {
        personaServicio.eliminar(persona);
        log.info("Ejecutada acción para eliminar persona");
        return "redirect:/";
    }

    @GetMapping("/registro")
    public String redirectLogin() {
        return "iniciarSesion";
    }

}