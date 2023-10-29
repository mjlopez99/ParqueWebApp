/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.TipoEspacioBean;
import com.mycompany.parqueowebapp.control.abstractDataAccess;
import com.mycompany.parqueowebapp.entitys.TipoEspacio;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped
public class FrmParaPruebas extends AbstractFrm<TipoEspacio> implements Serializable {

    @Inject
    TipoEspacioBean trClass;
    @Inject
    FacesContext Fc;

    String SQLFindById = "SELECT a FROM TipoEspacio a WHERE a.idTipoEspacio=1";
    String SQLCount = "SELECT COUNT(a) FROM TipoEspacio a";

    @Override
    public abstractDataAccess<TipoEspacio> getDataAccess() {
        return this.trClass;
    }

    @Override
    public String getIdObject(TipoEspacio object) {

        if (object != null && object.getIdTipoEspacio() != null) {
            return object.getIdTipoEspacio().toString();
        }
        return null;
    }

    @Override
    public TipoEspacio getObjectId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdTipoEspacio().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registroSelecionado = new TipoEspacio();
    }

    @Override
    public FacesContext getFC() {
        return this.Fc;
    }

//    metodos de prueba
    public int count() {
        EntityManager em = null;
        try {
            em = trClass.getEntityManger();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        if (em != null) {
            int q = Integer.parseInt(em.createQuery(SQLCount).getSingleResult().toString());
            System.out.println("Cantidad de registros: " + q);
            return q;
        }
        return 0;
    }
    public TipoEspacio findById() {
        EntityManager em = null;
        try {
            em = trClass.getEntityManger();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        if (em != null) {
            return (TipoEspacio) em.createQuery(SQLFindById).getSingleResult();
        }
        return null;
    }

}
