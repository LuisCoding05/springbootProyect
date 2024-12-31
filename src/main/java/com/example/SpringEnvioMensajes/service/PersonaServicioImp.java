package com.example.SpringEnvioMensajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringEnvioMensajes.model.Persona;
import com.example.SpringEnvioMensajes.repository.IpersonaDao;;



@Service // Marca la clase como un componente de servicio.
/**
 * Clase que implementa la interfaz PersonaServicio.
 */
public class PersonaServicioImp implements PersonaServicio { // Implementa la interfaz PersonaServicio.
    @Autowired // Inyecta la interfaz IpersonaDao en el servicio.
    private IpersonaDao personaDao; // Inicializa el repositorio de personas.

    @Override // Anotación que indica que el método sobrescrito es de la superclase.
    @Transactional(readOnly = true) // Anotación que indica que el método es de solo lectura.
    public List<Persona> listar() { // Método que lista todas las personas.
       return (List<Persona>) personaDao.findAll(); // Devuelve una lista de personas.
    }

    @Override // Anotación que indica que el método sobrescrito es de la superclase.
    @Transactional // Anotación que indica que el método es transaccional.
    public void guardar(Persona persona) { // Método que guarda una persona.
       personaDao.save(persona); // Guarda la persona en la base de datos.
    }

    @Override // Anotación que indica que el método sobrescrito es de la superclase.
    @Transactional // Anotación que indica que el método es transaccional.
    public void eliminar(Persona persona) { // Método que elimina una persona.
       personaDao.delete(persona); // Elimina la persona de la base de datos.
    }

    @Override // Anotación que indica que el método sobrescrito es de la superclase.
    @Transactional(readOnly = true) // Anotación que indica que el método es de solo lectura.
    public Persona encontrarPersona(Persona persona) { // Método que encuentra una persona.
        return personaDao.findById(persona.getId()).orElse(null); // Devuelve la persona encontrada.
    }

    @Override // Anotación que indica que el método sobrescrito es de la superclase.
    public Persona encontrarPrimeraPersona() { // Método que encuentra la primera persona.
        return personaDao.findFirstByOrderByIdAsc(); // Devuelve la primera persona encontrada.
    }

}
