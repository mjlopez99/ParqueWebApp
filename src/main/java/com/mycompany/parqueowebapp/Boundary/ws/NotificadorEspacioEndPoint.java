/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.ws;

import com.mycompany.parqueowebapp.control.ManagerSessionWS;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;


@Named
@ApplicationScoped
@ServerEndpoint(value = "/notificadorws")
public class NotificadorEspacioEndPoint implements Serializable{
    @Inject
    ManagerSessionWS msw;
    // al establecerce una conecion inmediatamente se ejecitara este metodo
    @OnOpen
    public  void conecto(Session s){
        msw.agregar(s);
        System.out.println("se conecto");
    }
    @OnClose
    public  void cerrar(Session s){
        msw.eliminar(s);
        System.out.println("se conecto");
    }
    public void PropargarMensaje(String mensaje) throws IOException {
        for (Session session : msw.getSessiones()) {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(mensaje);
            }
        }
    }
}
