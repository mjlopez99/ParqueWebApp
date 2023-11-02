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
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

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
    Espacio espacioSelecionado;

    public Espacio getEspacioSelecionado() {
        return espacioSelecionado;
    }

    public void setEspacioSelecionado(Espacio espacioSelecionado) {
        this.espacioSelecionado = espacioSelecionado;
    }

    @Override
    public abstractDataAccess<Espacio> getDataAccess() {
        return eb;
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
    public List<Espacio> cargarDatos(int first, int page) {
        if (this.idArea != null) {
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

    public void selecionarRegistro(int idAREA) {
        setIdArea(idAREA);
        InicializarRegistros();
    }
    //logica para nuevo lazyData
     private void InicializarRegistros() {
        this.modelo = new LazyDataModel<Espacio>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return eb.countByIdArea(idArea);
            }

            @Override
            public List<Espacio> load(int inicio, int pagina, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                return obtenereEspacioPorAreas(idArea,inicio,pagina);
            }

            @Override
            public String getRowKey(Espacio object) {
                if (object != null) {
                    return getIdObject(object);
                }
                return null;
            }

            @Override
            public Espacio getRowData(String rowKey) {
                if (rowKey != null) {
                    return getObjectId(rowKey);

                }
                return null;
            }

        };

    }
    public void onEspacioSelect(SelectEvent event) {
        System.out.println("Método onEspacioSelect llamado.");
        Espacio espacioSeleccionado = (Espacio) event.getObject();
        System.out.println("Espacio seleccionado: " + espacioSeleccionado);
    }
    public void LogParProbar() {
        System.out.println("la selecion fue un exito");
    }
     @Override
    public int contar() {
        if (idArea != null) {
            return this.eb.countByIdArea(idArea);
        }
        return 0;
    }
    public List<Espacio> obtenereEspacioPorAreas(Object IdArea,int inicio,int pagina) {
        if (IdArea != null) {
            return eb.findByIdArea(idArea, inicio, pagina);
        }

        return Collections.EMPTY_LIST;
    }

}
