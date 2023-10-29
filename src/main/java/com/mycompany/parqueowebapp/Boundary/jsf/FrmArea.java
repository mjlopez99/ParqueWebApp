/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.AreaBean;
import com.mycompany.parqueowebapp.control.abstractDataAccess;
import com.mycompany.parqueowebapp.entitys.Area;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped

public class FrmArea extends AbstractFrm<Area> implements Serializable {

    @Inject
    FrmEspacio frmEspacio;
    @Inject
    AreaBean aBean;
    @Inject
    FacesContext Fc;

    TreeNode raiz;
    TreeNode nodoSeleccionado;

    public TreeNode getNodoSelecionado() {
        return nodoSeleccionado;
    }

    public void setNodoSelecionado(TreeNode<Area> nodoSelecionado) {
        this.nodoSeleccionado = nodoSelecionado;
    }
    @PostConstruct
    @Override
    public void inicializar() {
        super.inicializar();
        System.out.println("continuamos");
        this.raiz = new DefaultTreeNode(new Area(), null);
        List<Area> lista = aBean.findRaices(0, 10);
        System.out.println("el tamaño es : "+lista.size());
//        if (lista != null && !lista.isEmpty()) {
//
//            for (Area next : lista) {
//                if (next.getIdAreaPadre() == null) {
//                    this.generarArbol(raiz, next);
//                }
//            }
//        }
    }
    public void generarArbol(TreeNode padre, Area actual) {
    
    DefaultTreeNode nuevoPadre = new DefaultTreeNode(actual, padre);

    List<Area> hijos = this.aBean.findByIdPadre(actual.getIdArea(), 0, 25);
    for (Area hijo : hijos) {
        generarArbol(nuevoPadre, hijo);
    }
}


    // Otros métodos y acciones según tus requisitos
    public TreeNode getRaiz() {
        return raiz;
    }

    public void setRaiz(TreeNode raiz) {
        this.raiz = raiz;
    }

    public TreeNode getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado(TreeNode nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    public void seleccionarNodoListener(NodeSelectEvent nse) {
        this.registroSelecionado = (Area) nse.getTreeNode().getData();
        this.selecionarRegistro();
        if (this.registroSelecionado != null && this.registroSelecionado.getIdArea() != null && this.frmEspacio != null) {
            this.frmEspacio.setIdArea(this.registroSelecionado.getIdArea());
        }
    }

    public FrmEspacio getFrmEspacio() {
        return frmEspacio;
    }

    @Override
    public abstractDataAccess<Area> getDataAccess() {
        return aBean;
    }

    @Override
    public String getIdObject(Area object) {

        if (object != null && object.getIdArea() != null) {
            return object.getIdArea().toString();
        }
        return null;
    }

    @Override
    public Area getObjectId(String id) {
        if (id != null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return modelo.getWrappedData().stream().filter(r -> r.getIdArea().toString().equals(id)).collect(Collectors.toList()).get(0);

        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        Area padre = this.registroSelecionado;
        this.registroSelecionado = new Area();
        this.registroSelecionado.setIdAreaPadre(padre);
    }

    @Override
    public List<Area> cargarDatos(int primero, int tamanio) {
        Integer idPadre = null;
        if (this.registroSelecionado != null) {
            idPadre = registroSelecionado.getIdArea();
        }
        List<Area> lista = aBean.findByIdPadre(idPadre, 0, 10);
        return lista;
    }

    @Override
    public FacesContext getFC() {
        return Fc;
    }

}
