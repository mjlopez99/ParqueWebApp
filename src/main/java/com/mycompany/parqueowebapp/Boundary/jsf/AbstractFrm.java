/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.abstractDataAccess;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author mjlopez
 * @param <T>
 */
public abstract class AbstractFrm<T> implements Serializable {

    public abstract abstractDataAccess<T> getDataAccess();

    public abstract String getIdObject(T object);

    public abstract T getObjectId(String id);

    public abstract void instanciarRegistro();

    LazyDataModel<T> modelo;
    EstadosCRUD estado = EstadosCRUD.NINGUNO;
    T registroSelecionado = null;

    public abstract FacesContext getFC();

    @PostConstruct
    public void inicializar() {
        InicializarRegistros();
    }

    private void InicializarRegistros() {
        this.modelo = new LazyDataModel<T>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                abstractDataAccess<T> clBean = null;
                int Resultado = 0;
                try {
                    clBean = getDataAccess();
                    Resultado = clBean.count();
                    return Resultado;
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                }
                return Resultado;
            }

            @Override
            public List<T> load(int inicio, int pagina, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                abstractDataAccess<T> clBean = null;
                List<T> listaResultados = null;
                try {
                    clBean = getDataAccess();
                    listaResultados = clBean.findRange(inicio, pagina);
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                } finally {
                    if (listaResultados == null) {
                        listaResultados = Collections.emptyList();
                    }
                }
                return listaResultados;
            }

            @Override
            public String getRowKey(T object) {
                if (object != null) {
                    return getIdObject(object);
                }
                return null;
            }

            @Override
            public T getRowData(String rowKey) {
                if (rowKey != null) {
                    return getObjectId(rowKey);

                }
                return null;
            }

        };

    }

    public void btnNuevoHandler(ActionEvent e) {
        this.estado = EstadosCRUD.NUEVO;
        this.instanciarRegistro();

    }

    public void selecionarRegistro() {
        this.estado = EstadosCRUD.MODIFICAR;
    }

    public void btnGuardarHandler(ActionEvent e) {
        FacesMessage mensaje = null;
        try {
            abstractDataAccess<T> clBean = null;
            clBean = getDataAccess();
            clBean.create(registroSelecionado);
            this.estado = EstadosCRUD.NINGUNO;
            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "registro guardado exitosamentes",
                    "se creo el registro");
            getFC().addMessage(null, mensaje);

            return;
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "registro no se pudo guardar",
                "no se creo el registro");
        getFC().addMessage(null, mensaje);
        this.registroSelecionado = null;
    }

    public void btnCancelarHandler(ActionEvent ex) {
        this.registroSelecionado = null;
        this.estado = EstadosCRUD.NINGUNO;
    }

    public void btnModificarHandler(ActionEvent ex) {
        T modificado = null;

        try {
            abstractDataAccess<T> clBean = null;
            clBean = getDataAccess();
            modificado = clBean.modify(registroSelecionado);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        if (modificado != null) {
            //notificar que se elimino3
            this.estado = EstadosCRUD.NINGUNO;
            this.registroSelecionado = null;
            return;
        }
        //
    }

    public void btneEliminarHandler(ActionEvent ex) {
        try {
            abstractDataAccess<T> clBean = null;
            clBean = getDataAccess();
            clBean.delete(registroSelecionado);
            this.estado = EstadosCRUD.NINGUNO;
            this.registroSelecionado = null;
            return;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public List<T> cargarDatos(int primero, int tamanio) {
        List<T> resultado = null;

        try {
            abstractDataAccess<T> clBean = getDataAccess();
            resultado = clBean.findRange(primero, tamanio);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if (resultado == null) {
                resultado = Collections.EMPTY_LIST;
            }
        }
        return resultado;
    }

    public int contar() {
        int resultado = 0;

        try {
            abstractDataAccess<T> clBean = getDataAccess();
            resultado = clBean.count();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return resultado;
    }

    public T getRegistroSelecionado() {
        return registroSelecionado;
    }

    public void setRegistroSelecionado(T registroSelecionado) {
        this.registroSelecionado = registroSelecionado;
    }

    public LazyDataModel<T> getModelo() {
        return this.modelo;
    }

    public EstadosCRUD getEstado() {
        return estado;
    }

    public void setEstado(EstadosCRUD estado) {
        this.estado = estado;
    }
}
