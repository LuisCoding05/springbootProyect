package com.example.SpringEnvioMensajes.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@Controller
public class MiControladorErrores implements ErrorController { // Implementamos la interfaz ErrorController

    @RequestMapping("/error") // Ruta para manejar errores
    public String handleError(HttpServletRequest request) { // Método para manejar errores
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); // Obtenemos el código de estado del error

        if (status != null) { // Si el código de estado no es nulo
            int statusCode = Integer.parseInt(status.toString()); // Convertimos el código de estado a entero

            switch (statusCode) { // Evaluamos el código de estado
                
                case 404: // Si el código de estado es 404
                    return "error/404"; // Retornamos la vista de error 404
                /* 
                case 403:
                    return "error/403";
                    */
                case 500: // Si el código de estado es 500
                    return "error/500"; // Retornamos la vista de error 500
                default: // Si el código de estado no es 404 ni 500
                    return "error/generic"; // Retornamos la vista de error genérico
            }
        }

        return "error/generic";
    }

    @Bean // Bean para manejar errores
    public String getErrorPath() { // Método para obtener la ruta de errores
        return "/error"; // Retornamos la ruta de errores
    }
}
