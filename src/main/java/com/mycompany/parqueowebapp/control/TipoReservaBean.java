/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import com.mycompany.parqueowebapp.entitys.TipoReserva;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author mjlopez
 */
@Stateless
//indica que la clase es un bean sin estado, lo que significa que no mantiene información de estado entre llamadas o  bean es independiente de las anteriores y siguientes
//y puede ser instanciado por el contenedor EJB según sea necesario. 
@LocalBean
//se utiliza para especificar que no se proporciona una interfaz local específica para el bean.Esto significa que los métodos de este EJB pueden ser invocados por clientes
//locales sin necesidad de una interfaz explícita.
public class TipoReservaBean extends abstractDataAccess<TipoReserva> implements Serializable{

    // "serializable" se refiere a la capacidad de un objeto o una clase de ser convertido en una secuencia de bytes, y viceversa, para que pueda ser almacenado en un archivo,
//    transmitido a través de una red o guardado en memoria de manera persistente. 
     @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
        EntityManager em;

    public TipoReservaBean() {
        super(TipoReserva.class);
    }

    /**
     almacena un registro en db
     * @return 
     * @throws IllegalStateException si ocurre un  error en repositoria
     * @throws  IllegalArgumentException si el paametro es nulo
     */

    @Override
    public EntityManager getEntityManger() {
       return em;
    }

    @Override
    public String getOrdenAsc() {
       return "idTipoReserva";
    }

    
}
