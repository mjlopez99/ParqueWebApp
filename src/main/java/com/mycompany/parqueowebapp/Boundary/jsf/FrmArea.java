/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.AreaBean;
import com.mycompany.parqueowebapp.control.EspacioBean;
import com.mycompany.parqueowebapp.control.abstractDataAccess;
import com.mycompany.parqueowebapp.entitys.Area;
import com.mycompany.parqueowebapp.entitys.Espacio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped

public class FrmArea extends AbstractFrm<Area> implements Serializable {

    private TreeNode<Area> root;
    private TreeNode<Area> nodoSelecionado;

    @Inject
    private FrmEspacio frme;
    @Inject
    private AreaBean ab;
    @Inject
    EspacioBean Eb;
    @Inject
    FacesContext Fc;

    public TreeNode<Area> getNodoSelecionado() {
        return nodoSelecionado;
    }

    public void setNodoSelecionado(TreeNode<Area> nodoSelecionado) {
        this.nodoSelecionado = nodoSelecionado;
    }

    @PostConstruct
    public void InicializarRegistros() {

        // Crear el nodo raíz y construir el árbol recursivamente
        root = new DefaultTreeNode<>(new Area(), null);
        
        // Obtener áreas principales (aquellas sin un área padre o igual a null)
        List<Area> areasRaiz = ab.obtenerAreasPrincipales();

        for (Area area : areasRaiz) {
            TreeNode areaNode = new DefaultTreeNode(area, root);

            // Luego, para cada área, agregar sus subáreas
            agregarSubareas(area, areaNode);
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        TreeNode<Area> ns = event.getTreeNode();
        setNodoSelecionado(ns);
        setRegistroSelecionado(ns.getData());
        this.selecionarRegistro();

        if (this.registroSelecionado != null && this.registroSelecionado.getIdArea() != null && frme != null) {
                this.frme.setIdArea(this.registroSelecionado.getIdArea());
                System.out.println("el id de drme es "+this.frme.getIdArea());
            }
        System.out.println("el id del area selecionadaes: "+this.registroSelecionado.getIdArea()+" el frmespacio es: "+frme);
        }

    

    private void agregarSubareas(Area areaPadre, TreeNode padreNode) {
        List<Area> subareas = ab.obtenerSubAreas(areaPadre);

        for (Area subarea : subareas) {
            TreeNode subareaNode = new DefaultTreeNode(subarea, padreNode);
            agregarSubareas(subarea, subareaNode);  // Llamada recursiva para manejar subáreas anidadas
        }
    }

    // Otros métodos y acciones según tus requisitos
    public TreeNode<Area> getRoot() {
        return root;
    }

    public void colapsarTodosLosNodos() {
        colapsarRecursivamente(root);
    }

    private void colapsarRecursivamente(TreeNode<Area> nodo) {
        for (TreeNode<Area> hijo : nodo.getChildren()) {
            colapsarRecursivamente(hijo);
        }
        nodo.setExpanded(false);
    }

    @Override
    public void btnCancelarHandler(ActionEvent ex) {
        super.btnCancelarHandler(ex);
        setNodoSelecionado(null);
        colapsarTodosLosNodos();
    }

    @Override
    public abstractDataAccess<Area> getDataAccess() {
        return this.ab;
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
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdArea().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registroSelecionado = new Area();
    }

    @Override
    public FacesContext getFC() {
        return this.Fc;
    }

    public FrmEspacio getFrme() {
        return frme;
    }

    @Override
    public LazyDataModel<Area> getModelo() {
        return super.getModelo(); //
    }

    public EspacioBean getEb() {
        return Eb;
    }
    

}
