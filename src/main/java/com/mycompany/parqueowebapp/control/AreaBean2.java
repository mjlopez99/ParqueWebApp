package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.entitys.Area;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class AreaBean2 extends abstractDataAccess<Area> implements Serializable {

    @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
    EntityManager em;

    public AreaBean2() {
        super(Area.class);
    }

    @Override
    public EntityManager getEntityManger() {
        return em;
    }

    @Override
    public String getOrdenAsc() {
        return "idArea";
    }

    public List<Area> obtenerAreasPrincipales() {
    try {
        return em.createQuery("SELECT a FROM Area a WHERE a.idAreaPadre IS NULL", Area.class).getResultList();
    } catch (Exception e) {
        // Manejo de excepciones: imprime o registra el error
        e.printStackTrace();
        return null; // o manejo apropiado según tus necesidades
    }
}

public List<Area> obtenerSubAreas(Area areaPadre) {
    try {
        return em.createQuery("SELECT a FROM Area a WHERE a.idAreaPadre = :idArea", Area.class)
            .setParameter("idArea", areaPadre)
            .getResultList();
    } catch (Exception e) {
        // Manejo de excepciones: imprime o registra el error
        e.printStackTrace();
        return null; // o manejo apropiado según tus necesidades
    }
}

}
