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
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author mjlopez
 */
@Named
@Dependent
public class FrmEspacio extends AbstractFrm<Espacio> implements Serializable {

    @Inject
    EspacioBean eb;
    @Inject
    FacesContext fc;
    Integer idArea;

    @Override
    public abstractDataAccess<Espacio> getDataAccess() {
        return eb;
    }

    @Override
    public String getIdObject(Espacio object) {
        if (object != null && object.getIdEspacio() != null) {
            return object.getIdArea().toString();
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
        System.out.println("yamen");
        this.registroSelecionado = new Espacio();
        if (this.getIdArea() != null) {
            this.registroSelecionado.setIdArea(new Area(idArea));
        }
        this.registroSelecionado.setActivo(Boolean.TRUE);
        
    }

    @Override
    public FacesContext getFC() {

        return fc;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    @Override
    public LazyDataModel<Espacio> getModelo() {
        return super.getModelo();
    }
    @Override
    public List<Espacio> cargarDatos(int first,int page) {
        if (this.idArea!=null) {
            return this.eb.findByIdArea(idArea, first, page);
        }

        return Collections.EMPTY_LIST;
    }

    public EspacioBean getEb() {
        return eb;
    }

    public void setEb(EspacioBean eb) {
        this.eb = eb;
    }

    public FacesContext getFc() {
        return fc;
    }

    public void setFc(FacesContext fc) {
        this.fc = fc;
    }
    @Override
    public int contar(){
        if (idArea!=null) {
            return this.eb.countByIdArea(idArea);
        }
        return 0;
    }
    public List<Espacio> obtenereEspacioPorAreas(Object IdArea){
        if (IdArea!=null) {
            return eb.findByIdArea(idArea, 0, 10);
        }
        
    return Collections.EMPTY_LIST;
    }
    @Override
    public void selecionarRegistro() {
        System.out.println("selecionado amigo");
    }
    
}
