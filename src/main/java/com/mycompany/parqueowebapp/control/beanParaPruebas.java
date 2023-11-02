/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.entitys.TipoReserva;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mjlopez
 */
@LocalBean
@Stateless
public class beanParaPruebas implements Serializable {

    @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
    EntityManager em;

    public void create(TipoReserva registtro) {
        if (registtro != null) {
            try {
                if (em != null) {
                    em.persist(registtro);
                    return;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }

    public List<TipoReserva> findRange(int first, int page) {

        if (first >= 0 && page > 0) {
            try {
                if (em != null) {
                    Query q = em.createNamedQuery("TipoReserva.findAll");
                    q.setFirstResult(first);
                    q.setMaxResults(page);
                    return (q.getResultList());
                }
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
            throw new IllegalStateException();

        }

        return Collections.EMPTY_LIST;
    }
    public List<Object> Lista(String jpql) {
        String jpqlQuery = jpql;
        Query query = em.createQuery(jpqlQuery);

        // Ejecuta la consulta y obtén los resultados como una lista de objetos
        List<Object> resultados = query.getResultList();

        // Itera sobre los resultados (pueden ser Strings u otros tipos según la consulta)
        for (Object resultado : resultados) {
            jpql=jpql+"\n"+resultado;
        }
        return resultados;
    }
    public Object unRegistro(String jpql) {
        String jpqlQuery = jpql;
        Query query = em.createQuery(jpqlQuery);

        // Ejecuta la consulta y obtén los resultados como una lista de objetos
        List<Object> resultado=new ArrayList<>();
        
        resultado.add(query.getResultList().get(0));
        
        return resultado.get(0);
    }
    public int cantidad(String jpql) {
        String jpqlQuery = jpql;
        Query query = em.createQuery(jpqlQuery);

        // Ejecuta la consulta y obtén los resultados como una lista de objetos
        int resultado=((Long)query.getSingleResult()).intValue();
        
        System.out.println("la cantidad de registros es: "+resultado);
          return resultado;
    }

}
