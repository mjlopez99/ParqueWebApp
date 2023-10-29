/**
 *
 * @author mjlopez
 */
package com.mycompany.parqueowebapp.Boundary.jsf;

import com.mycompany.parqueowebapp.control.AreaBean2;
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
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.TreeNode;

/**
 *
 * @author mjlopez
 */
@Named
@ViewScoped

public class FrmArea2 extends AbstractFrm<Area> implements Serializable {

    private TreeNode<Area> root;
    private TreeNode<Area> nodoSelecionado;

    @Inject
    FrmEspacio frme;
    @Inject
    AreaBean2 ab;
    @Inject
    EspacioBean Eb;
    @Inject
    FacesContext Fc;
    List<Espacio> ListaEspacios;
    Espacio espacioRegistrado;

    public Espacio getEspacioRegistrado() {
        return espacioRegistrado;
    }

    public void setEspacioRegistrado(Espacio espacioRegistrado) {
        this.espacioRegistrado = espacioRegistrado;
    }
    public List<Espacio> getListaEspacios() {
        return ListaEspacios;
    }
    LazyDataModel<Espacio> modelo2;

    public TreeNode<Area> getNodoSelecionado() {
        return nodoSelecionado;
    }

    public void setNodoSelecionado(TreeNode<Area> nodoSelecionado) {
        this.nodoSelecionado = nodoSelecionado;
    }

    @PostConstruct
    @Override
    public void inicializar() {
        if (root == null) {
            super.inicializar();
            // Crear el nodo raíz y construir el árbol recursivamente

        }
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

    if (frme != null && this.registroSelecionado != null && this.registroSelecionado.getIdArea() != null) {
        this.frme.setIdArea(this.registroSelecionado.getIdArea());
        this.frme.inicializar();

        ListaEspacios = this.frme.obtenereEspacioPorAreas(registroSelecionado.getIdArea());
    }
}


    public LazyDataModel<Espacio> getModelo2() {
        return modelo2;
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
    public void res(SelectEvent event) {
    System.out.println("Método res invocado.");
    // Resto de la lógica
}

    
}
