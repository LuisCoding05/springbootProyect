package com.example.SpringEnvioMensajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringEnvioMensajes.model.Persona;
import com.example.SpringEnvioMensajes.repository.IpersonaDao;;



@Service
public class PersonaServicioImp implements PersonaServicio {
    @Autowired
    private IpersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listar() {
       return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
       personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
       personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getId()).orElse(null);
    }

    @Override
    public Persona encontrarPrimeraPersona() {
        return personaDao.findFirstByOrderByIdAsc();
    }

}
