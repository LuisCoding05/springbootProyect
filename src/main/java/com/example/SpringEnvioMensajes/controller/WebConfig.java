package com.example.SpringEnvioMensajes.controller;

import java.util.Locale;

import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;




    


@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura un SessionLocaleResolver.
     * SessionLocaleResolver permite almacenar la configuración regional en la sesión del usuario.
     * Establece el idioma por defecto a español utilizando el código de lenguaje 'es'.
     *
     * @return SessionLocaleResolver configurado.
     */
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.forLanguageTag("es")); // Establecer el idioma por defecto a español
        return slr;
    }

    /**
     * Configura un LocaleChangeInterceptor.
     * Intercepta las solicitudes HTTP para cambiar la configuración regional basado en un parámetro de solicitud (lang).
     *
     * @return LocaleChangeInterceptor configurado.
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang"); // El nombre del parámetro que cambiará el idioma
        return lci;
    }

    /**
     * Añade el LocaleChangeInterceptor al registro de interceptores.
     *
     * @param registry El registro de interceptores de Spring.
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()); // Añadir el interceptor configurado
    }

    /**
     * Configura un MessageSource.
     * MessageSource permite la internacionalización de mensajes.
     * Establece el archivo de propiedades de mensajes en 'classpath:messages/messages'.
     *
     * @return MessageSource configurado.
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    /**
     * Añade controladores de vista para las rutas de acceso a las páginas de error.
     * @param ingreso es el registro de controladores de vista.
     * Se usa para páginas en las que no se necesita lógica adicional. como 
     * páginas de error. o páginas de estáticas
     */
    @Override
    public void addViewControllers (@NonNull ViewControllerRegistry ingreso){
        ingreso.addViewController ("/") .setViewName ("index");
        ingreso.addViewController ("error/403").setViewName("error/403");
        ingreso.addViewController ("error/404").setViewName("error/404");
    }


}