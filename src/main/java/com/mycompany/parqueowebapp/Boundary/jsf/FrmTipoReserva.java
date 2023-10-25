/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.TipoReservaBean;
import com.mycompany.parqueowebapp.control.abstractDataAccess;
import com.mycompany.parqueowebapp.entitys.TipoReserva;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped
public class FrmTipoReserva extends AbstractFrm<TipoReserva> implements Serializable {

    @Inject
    TipoReservaBean trBean;
    @Inject
    FacesContext Fc;

    @Override
    public abstractDataAccess<TipoReserva> getDataAccess() {
        return this.trBean;
    }

    @Override
    public String getIdObject(TipoReserva object) {

        if (object != null && object.getIdTipoReserva() != null) {
            return object.getIdTipoReserva().toString();
        }
        return null;
    }

    @Override
    public TipoReserva getObjectId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdTipoReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registroSelecionado=new TipoReserva();
    }

    @Override
    public FacesContext getFC() {
        return this.Fc;
    }
}
