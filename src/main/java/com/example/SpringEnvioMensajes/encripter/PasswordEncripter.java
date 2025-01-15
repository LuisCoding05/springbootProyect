package com.example.SpringEnvioMensajes.encripter;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncripter {
    // Encripta una contraseña utilizando BCryptPasswordEncoder
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner entrada = new Scanner(System.in);
        // Variable para controlar el bucle
        boolean finish = false;
        // Bucle para encriptar contraseñas
        System.out.println("Introduce la contraseña a encriptar: ");
        System.out.println("Escribe 'close' para salir");
        while (!finish) {
            // Leer la contraseña del usuario
            String password = entrada.nextLine();
            // Salir del bucle si el usuario escribe 'close'
            if (password.equalsIgnoreCase("close")) {
                finish = true;
                entrada.close();
            }
            // Encriptar la contraseña si no es 'close'
            else{
                String passwordEncripted = encriptar(password);
                System.out.println("Password : " + password);
                System.out.println("Password encriptado: " + passwordEncripted);
            }
        }
    }
    // Encripta una contraseña utilizando BCryptPasswordEncoder
    public static String encriptar(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
