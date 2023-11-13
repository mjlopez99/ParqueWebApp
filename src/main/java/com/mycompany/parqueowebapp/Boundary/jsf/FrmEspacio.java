/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.EspacioBean;
import com.mycompany.parqueowebapp.control.abstractDataAccess;
import com.mycompany.parqueowebapp.entitys.Area;
import com.mycompany.parqueowebapp.entitys.Espacio;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mjlopez
 */
@Named
@Dependent
public class FrmEspacio extends AbstractFrm<Espacio> implements Serializable {

    @Inject
    FacesContext fc;
    @Inject
    EspacioBean eBean;
    Integer idArea;

    @Override
    public abstractDataAccess<Espacio> getDataAccess() {
        return eBean;
    }

    @Override
    public FacesContext getFC() {
        return fc;
    }

    @Override
    public String getIdObject(Espacio object) {
        if (object != null && object.getIdEspacio() != null) {
            return object.getIdEspacio().toString();
        }
        return null;
    }

    @Override
    public Espacio getObjectId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdEspacio().toString().equals(id)).collect(Collectors.toList()).get(0);

        }
        return null;

    }

    @Override
    public void instanciarRegistro() {
        this.registroSelecionado = new Espacio();
        if (this.idArea != null) {
            this.registroSelecionado.setIdArea(new Area(idArea));

        }
        this.registroSelecionado.setActivo(true);

    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    @Override
    public List<Espacio> cargarDatos(int primero, int tamanio) {
        if (this.idArea != null) {
            List<Espacio> resultado = this.eBean.findByIdArea(idArea, primero , tamanio);
            System.out.println(this.idArea);
            resultado.forEach(e->System.out.println(e));
             return resultado;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public int contar() {
        if (this.idArea != null) {
            return this.eBean.contarByIdArea(idArea);
        }
        return 0;
    }

}
