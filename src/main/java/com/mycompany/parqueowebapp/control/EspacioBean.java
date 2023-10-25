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
public class EspacioBean extends abstractDataAccess<Espacio> implements Serializable{
    @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
    EntityManager em;
    public EspacioBean() {
        super(Espacio.class);
    }

    @Override
    public EntityManager getEntityManger() {
         return em;
    }

    @Override
    public String getOrdenAsc() {
         return "idEspacio";
    }

    public List<Espacio> findByIdArea(final Integer idArea,int first,int page) {
        
        if (first >= 0 && page > 0) {
            try {
                em = getEntityManger();
                if (em != null) {
                    Query q = em.createNamedQuery("Espacio.findByIdArea");
                   q.setParameter("idArea", idArea);
                   
                    q.setFirstResult(first);
                    q.setMaxResults(page);
                    List<Espacio> e=q.getResultList();
                    leerdatos(e);
                    return e;

                }
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
            throw new IllegalStateException();

        }

        return Collections.EMPTY_LIST;
    }
    public int countByIdArea(final Integer idArea){
        if (idArea!=null) {
            if (em!=null) {
                Query q = em.createNamedQuery("Espacio.countByIdArea");
                q.setParameter("idArea", idArea);
                System.out.println("respuesta");
                return ((Long) q.getSingleResult()).intValue();
            }else{
                System.out.println("no hay nada");
            }
        }
        return 0;
    }
    public  void leerdatos(List<Espacio> e){
        System.out.println("datos:");
        e.forEach(System.out::println);
    
    }
    
    
}
