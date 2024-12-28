package com.example.SpringEnvioMensajes.service;

import java.util.List;

import com.example.SpringEnvioMensajes.model.Persona;

public interface PersonaServicio {
    public List<Persona> listar();

    public void guardar(Persona persona);

    public void eliminar(Persona persona);

    public Persona encontrarPersona(Persona persona);

    public Persona encontrarPrimeraPersona();

}
