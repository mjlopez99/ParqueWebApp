/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.TipoReservaBean;
import com.mycompany.parqueowebapp.entitys.TipoReserva;
import jakarta.annotation.PostConstruct;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped
public class FrmTipoReserva implements Serializable {

    @Inject
    TipoReservaBean TrBean;//cosas injectadas no estan disponibles en contructor
    List<TipoReserva> listaRegistros;
    LazyDataModel<TipoReserva> modelo;
    TipoReserva registroSelecionado = null;

    public FrmTipoReserva() {
    }

    @PostConstruct
    public void inicializar() {
        InicializarRegistros();
    }

    public void InicializarRegistros() {
        this.listaRegistros = TrBean.findRange(0, 100000);
        this.modelo=new LazyDataModel<TipoReserva>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
               return TrBean.count();
            }

            @Override
            public List<TipoReserva> load(int i, int i1, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
              return TrBean.findRange(i, i1);
            }

            @Override
            public String getRowKey(TipoReserva object) {
                if (object!=null && object.getIdTipoReserva()!=null) {
                    return object.getIdTipoReserva().toString();
                }
                return null;
            }

            @Override
            public TipoReserva getRowData(String rowKey) {
                if (rowKey!=null) {
                    return this.getWrappedData().stream().filter(r->r.getIdTipoReserva().toString().equals(rowKey)).
                            collect(Collectors.toList()).get(0);
                    
                }
                return null;
            }
            
        };
    }

    public void btnNuevoHandler(ActionEvent e) {
        this.registroSelecionado = new TipoReserva();
        
    }

    public void btnGuardarHandler(ActionEvent e) {
        this.TrBean.create(registroSelecionado);
        this.listaRegistros=this.TrBean.findRange(0, 10000);
        this.registroSelecionado = null;
    }

    public void btnSeleccionarHandler(ActionEvent ex) {
        Integer id = (Integer) ex.getComponent().getAttributes().get("Selecionado");
        if (id != null) {
            this.registroSelecionado = TrBean.findById(id);
        }

    }
    public void btnCancelarHandler(ActionEvent ex) {
        this.registroSelecionado=null;
    }

    public void btnModificarHandler(ActionEvent ex) {
        TipoReserva modificado = this.TrBean.modify(registroSelecionado);
        if (modificado != null) {
            //notificar que se elimino3
            this.listaRegistros=this.TrBean.findRange(0, 10000);
            this.registroSelecionado = null;
        }
        //
    }

    public TipoReservaBean getTrBean() {
        return TrBean;
    }

    public void setTrBean(TipoReservaBean TrBean) {
        this.TrBean = TrBean;
    }

    public List<TipoReserva> getListaRegistros() {
        return this.listaRegistros;
    }

    public void setListaRegistros(List<TipoReserva> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public TipoReserva getRegistroSelecionado() {
        return registroSelecionado;
    }

    public void setRegistroSelecionado(TipoReserva registroSelecionado) {
        this.registroSelecionado = registroSelecionado;
    }

    public LazyDataModel<TipoReserva> getModelo() {
        return this.modelo;
    }
    
}
