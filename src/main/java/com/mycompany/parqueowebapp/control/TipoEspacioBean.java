/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.entitys.TipoEspacio;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mjlopez
 */
@Stateless
@LocalBean
public class TipoEspacioBean extends abstractDataAccess<TipoEspacio> implements Serializable {

    @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
    EntityManager em;

    public TipoEspacioBean() {
        super(TipoEspacio.class);
    }

    @Override
    public EntityManager getEntityManger() {
        return em;
    }

    @Override
    public String getOrdenAsc() {
        return "idTipoEspacio";
    }
    public List<TipoEspacio> findeRangeSlow(int first, int pagesize){
        
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
                
                return this.findRange(first, pagesize);
    }
}
