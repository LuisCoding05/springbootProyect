package com.example.SpringEnvioMensajes.service;

import java.util.List;

import com.example.SpringEnvioMensajes.model.Persona;

/**
 * Interfaz que define los métodos que se pueden realizar sobre la entidad Persona.
 */
public interface PersonaServicio { // Interfaz que define los métodos que se pueden realizar sobre la entidad Persona.

    public List<Persona> listar(); // Método que lista todas las personas.

    public void guardar(Persona persona); // Método que guarda una persona.

    public void eliminar(Persona persona); // Método que elimina una persona.

    public Persona encontrarPersona(Persona persona); // Método que encuentra una persona.

    public Persona encontrarPrimeraPersona(); // Método que encuentra la primera persona.

}
