package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.entitys.Area;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

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

    public List<Area> obtenerAreasPrincipales() {
        return em.createQuery("SELECT a FROM Area a WHERE a.idAreaPadre IS NULL", Area.class).getResultList();
    }

    public List<Area> obtenerSubAreas(Area areaPadre) {
        return em.createQuery("SELECT a FROM Area a WHERE a.idAreaPadre = :idArea", Area.class)
                .setParameter("idArea", areaPadre)
                .getResultList();
    }
}
