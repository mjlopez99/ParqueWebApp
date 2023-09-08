/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import com.mycompany.parqueowebapp.entitys.TipoReserva;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author mjlopez
 */
@Stateless
@LocalBean
public class TipoReservaBean extends abstractDataAccess<TipoReserva> implements Serializable{

     @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
        EntityManager em;

    public TipoReservaBean() {
        super(TipoReserva.class);
    }

    /**
     almacena un registro en db
     @param registro entidad a guarfdar
     * @throws IllegalStateException si ocurre un  error en repositoria
     * @throws  IllegalArgumentException si el paametro es nulo
     */

    @Override
    public EntityManager getEntityManger() {
       return em;
    }

    
}
