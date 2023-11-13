/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped
public class frmNotificarEspacio implements Serializable{
    
    public String getEspaciosLibres(){
     return String.valueOf(System.currentTimeMillis());
    }
}
