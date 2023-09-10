/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mjlopez
 */
public abstract class abstractDataAccess<T> {

    final Class tipoDatos;

    public abstractDataAccess(Class t) {
        this.tipoDatos = t;
    }

    public abstract EntityManager getEntityManger();

    public void create(T registtro) {
        if (registtro != null) {
            try {
                EntityManager em = getEntityManger();
                if (em != null) {
                    em.persist(registtro);
                    return;
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }

    public List<T> findRange(int first, int page) {
        EntityManager em = null;

        if (first >= 0 && page > 0) {
            try {
                em = getEntityManger();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            if (em != null) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery cq = cb.createQuery(tipoDatos);
                Root<T> raiz = cq.from(tipoDatos);
                cq.select(raiz);
                cq.orderBy(cb.asc(raiz.get("idTipoReserva")));
                
                
                TypedQuery q = em.createQuery(cq);

                q.setFirstResult(first);
                q.setMaxResults(page);
                return (q.getResultList());

            }

        }
        return Collections.EMPTY_LIST;
    }
    public T findById(Object id) {
        EntityManager em = null;
        if (id!=null) {
            try {
                em = getEntityManger();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            if (em != null) {
                try {
                    return (T)em.find(tipoDatos, id);
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                }

            }throw  new IllegalStateException();

        }throw new IllegalArgumentException();
    }
    public T modify(T registro) {
        EntityManager em = null;
        try {
                em = getEntityManger();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        if (registro!=null) {
            if (em != null) {
                try {
                    return em.merge(registro);
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                }

            }throw  new IllegalStateException();

        }return null;
    }
    public int count() {
         EntityManager em = null;
        try {
                em = getEntityManger();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            if (em != null) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery cq = cb.createQuery(tipoDatos);
                Root<T> raiz = cq.from(tipoDatos);
                cq.select(cb.count(raiz));
                
                
                TypedQuery q = em.createQuery(cq);

                return Integer.parseInt(q.getSingleResult().toString());
            }
        return 0;
    }
    
}
