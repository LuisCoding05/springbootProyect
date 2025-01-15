package com.example.SpringEnvioMensajes.converter;

import com.example.SpringEnvioMensajes.model.Rol;

import org.springframework.lang.NonNull;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRolConverter implements Converter<String, Rol> {

    @Override
    public Rol convert(@NonNull String source) {
        Rol rol = new Rol();
        rol.setNombreRol(source);
        return rol;
    }
}
