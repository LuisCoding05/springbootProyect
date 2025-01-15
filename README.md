# Spring example proyect for web server

## Descripción

Este proyecto es una aplicación web desarrollada con Spring Boot que permite gestionar usuarios y personas. La aplicación incluye autenticación y autorización utilizando Spring Security, así como internacionalización con Thymeleaf.

## Características

- **Gestión de Usuarios**: Permite agregar, editar y eliminar usuarios.
- **Gestión de Personas**: Permite agregar, editar y eliminar personas.
- **Autenticación y Autorización**: Utiliza Spring Security para la autenticación y autorización de usuarios.
- **Internacionalización**: Soporte para múltiples idiomas utilizando Thymeleaf.

## Requisitos

- Java(JDK) 23 o superior
- Maven 3.9.9 o superior
- MySQL 8.0 o superior

## Configuración

### Base de Datos

1. Importar la base de datos de la carpeta database del fichero `database.sql` a una base de datos MySQL llamada `cursospring`.
2. Configurar las credenciales de la base de datos en el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/cursospring?useSSL=false&serverTimezone=UTC&allPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=1234
```

### Dependencias

Las dependencias del proyecto están gestionadas por Maven. Asegúrate de tener Maven instalado y ejecuta el siguiente comando para descargar las dependencias:

```bash
./mvnw clean install
```

## Ejecución

Para ejecutar la aplicación, utiliza el siguiente comando:

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en [http://localhost:5000](http://localhost:5000).

## Estructura del Proyecto

- **SpringEnvioMensajes**: Contiene el código fuente de la aplicación.
  - **controller**: Controladores de Spring MVC.
  - **model**: Entidades JPA.
  - **repository**: Repositorios JPA.
  - **service**: Servicios de la aplicación.
  - **encripter**: Clase para encriptar contraseñas.
- **resources**: Contiene los recursos de la aplicación.
  - **static**: Archivos estáticos como CSS y JavaScript.
  - **templates**: Plantillas Thymeleaf.
  - **application.properties**: Archivo de configuración de Spring Boot.
- **SpringEnvioMensajes**: Contiene las pruebas unitarias de la aplicación.

## Seguridad

La seguridad de la aplicación está configurada en la clase `SecurityConfig.java`. Utiliza `BCryptPasswordEncoder` para encriptar las contraseñas y `CustomUserDetailsService` para cargar los detalles del usuario.

## Internacionalización

La aplicación soporta múltiples idiomas utilizando Thymeleaf. Los archivos de mensajes se encuentran en `messages`.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request en el repositorio.
