/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.beanParaPruebas;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped
public class frmPruebas implements Serializable {
String sqlUsuarioRgistro;
String sqlUsuarioLista;
String sqlUsuarionumero;

Object registro;
List<Object> registros;
int cantidadResultado;
    

     @Inject
    beanParaPruebas bp;

    public void unRegistro() {
        setRegistro(bp.unRegistro(sqlUsuarioRgistro));
    }
    public void Lista() {
        setRegistros(bp.Lista(sqlUsuarioLista));
    }
    public void cantidad() {
        setCantidadResultado( bp.cantidad(sqlUsuarionumero));
    }

    public int getCantidadResultado() {
        return cantidadResultado;
    }

    public void setCantidadResultado(int cantidadResultado) {
        this.cantidadResultado = cantidadResultado;
    }
    
    public Object getRegistro() {
        return registro;
    }

    public void setRegistro(Object registro) {
        this.registro = registro;
    }

    public List<Object> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Object> registros) {
        this.registros = registros;
    }



   

    public String getSqlUsuarioRgistro() {
        return sqlUsuarioRgistro;
    }

    public void setSqlUsuarioRgistro(String sqlUsuarioRgistro) {
        this.sqlUsuarioRgistro = sqlUsuarioRgistro;
    }

    public String getSqlUsuarioLista() {
        return sqlUsuarioLista;
    }

    public void setSqlUsuarioLista(String sqlUsuarioLista) {
        this.sqlUsuarioLista = sqlUsuarioLista;
    }

    public String getSqlUsuarionumero() {
        return sqlUsuarionumero;
    }

    public void setSqlUsuarionumero(String sqlUsuarionumero) {
        this.sqlUsuarionumero = sqlUsuarionumero;
    }

}
