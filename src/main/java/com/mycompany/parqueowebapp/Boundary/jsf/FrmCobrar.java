/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.Boundary.ws.NotificadorEspacioEndPoint;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped
public class FrmCobrar implements Serializable{
    @Inject 
    NotificadorEspacioEndPoint ne;
    public void btnCobrar(ActionEvent ae) throws IOException{
        //TODO logica
        ne.PropargarMensaje("parqueo libre");
    }
}
