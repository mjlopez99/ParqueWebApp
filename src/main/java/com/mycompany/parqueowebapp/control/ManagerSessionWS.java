/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.websocket.Session;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mjlopez
 */
@ApplicationScoped
@Named
public class ManagerSessionWS implements Serializable{
    final Set<Session> SESSIONES=new HashSet<>();
    public void agregar( Session s) {
        this.SESSIONES.add(s);
    }
    public void eliminar( Session s) {
        this.SESSIONES.remove(s);
    }
    public Set<Session> getSessiones(){
    return this.SESSIONES;
    }
}
