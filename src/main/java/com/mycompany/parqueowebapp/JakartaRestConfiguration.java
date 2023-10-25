package com.mycompany.parqueowebapp;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
//@ApplicationPath("resources") sirve para especificar el prefijo de la ruta base para todos los recursos (como Servlets, filtros, etc.) 
//en una aplicación web. o esta anotación se utiliza para definir la ruta base para los recursos REST en la aplicación.
public class JakartaRestConfiguration extends Application {
    
}
