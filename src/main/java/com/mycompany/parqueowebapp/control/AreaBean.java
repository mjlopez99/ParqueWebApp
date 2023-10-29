package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.entitys.Area;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Stateless
@LocalBean
public class AreaBean extends abstractDataAccess<Area> implements Serializable {

    @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
    EntityManager em;

    public AreaBean() {
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

     public List<Area> findByIdPadre(final Integer idPadre, int primero, int tamanio) {
        if (idPadre != null && primero >= 0 && tamanio > 0) {
            if (em != null) {
                Query q = em.createNamedQuery("Area.findByIdArea");
                q.setParameter("idArea", idPadre);
                q.setFirstResult(primero);
                q.setMaxResults(tamanio);
                return q.getResultList();

            }  

        }
        return Collections.EMPTY_LIST;
    }

    public int contarByIdPadre(final Integer idPadre) {
        if (idPadre != null && em != null) {
            Query q = em.createNamedQuery("Area.coundByIdPadre");
            q.setParameter("idAreaPadre", idPadre);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }

    public List<Area> findRaices(int primero, int tamanio) {
        if (em != null) {
            Query q = em.createNamedQuery("Area.findRaices");
            q.setFirstResult(primero);
            q.setMaxResults(tamanio);
            return q.getResultList();
        }
        return Collections.EMPTY_LIST;
    }

}
