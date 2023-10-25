/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import jdk.jfr.Name;
import static org.primefaces.component.api.UICalendar.PropertyKeys.locale;

/**
 *
 * @author mjlopez
 */
@Named
@SessionScoped
public class SessionUsuario implements Serializable{
    Map<String, Object> idiomas=new HashMap<>();
    @Inject
    FacesContext fc;
    String idiomaSelecionado; 

    public String getIdiomaSelecionado() {
        return idiomaSelecionado;
    }

    public void setIdiomaSelecionado(String idiomaSelecionado) {
        this.idiomaSelecionado = idiomaSelecionado;
    }
    @PostConstruct
    public void inicializar() {
       this.inicializarIdiomas();
    }
    public void inicializarIdiomas() {
        idiomas.put("Español", new Locale("es"));
        idiomas.put("English", new Locale("en"));
        
    }

    public Map<String, Object> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Map<String, Object> idiomas) {
        this.idiomas = idiomas;
    }
    public void cambiatIdioma(ValueChangeEvent ev) {
        String idioma=ev.getNewValue().toString();
        for (Map.Entry<String, Object> idio : idiomas.entrySet()) {
            if (idio.getValue().toString().equalsIgnoreCase(idioma)) {
                fc.getViewRoot().setLocale((Locale) idio.getValue());
            }
        }
    }
    
}
