/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.entitys.Espacio;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mjlopez
 */
@Stateless
@LocalBean
public class EspacioBean extends abstractDataAccess<Espacio> implements Serializable {

    @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
    EntityManager em;

    public EspacioBean() {
        super(Espacio.class);
    }

    @Override
    public EntityManager getEntityManger() {
        return em;
    }
    
    public List<Espacio> findByIdArea(final Integer idArea, int primero, int tamanio) {
        if (idArea != null && primero >= 0 && tamanio > 0) {
            if (em != null) {
                Query q = em.createNamedQuery("Espacio.findByIdArea");
                q.setParameter("idArea", idArea);
                q.setFirstResult(primero);
                q.setMaxResults(tamanio);
                return q.getResultList();

            }  

        }
        return Collections.EMPTY_LIST;
    }

    public int contarByIdArea(final Integer idArea) {
        if (idArea != null && em != null) {
            Query q = em.createNamedQuery("Espacio.countByIdArea");
            q.setParameter("idArea", idArea);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }

    @Override
    public String getOrdenAsc() {
        return "idEspacio";
    }

}
