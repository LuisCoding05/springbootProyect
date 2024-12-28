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
public class MiControladorErrores implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            switch (statusCode) {
                
                case 404:
                    return "error/404";
                /* 
                case 403:
                    return "error/403";
                    */
                case 500:
                    return "error/500";
                default:
                    return "error/generic";
            }
        }

        return "error/generic";
    }
    @Bean
    public String getErrorPath() {
        return "/error";
    }
}
