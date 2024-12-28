package com.example.SpringEnvioMensajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
// Clase principal que inicia la aplicación Spring Boot.
@SpringBootApplication
// Anotación que combina @Configuration, @EnableAutoConfiguration y @ComponentScan.
@ComponentScan(basePackages = {"com.example"})
public class SpringEnvioMensajesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEnvioMensajesApplication.class, args);
    }

}