/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mjlopez
 * @param <T>
 */
public abstract class abstractDataAccess<T> {
//Una clase genérica, también conocida como clase parametrizada, es una clase en la programación que puede funcionar con tipos de datos diferentes. 
//    Permite que se especifique el tipo de datos que una clase va a manipular o contener en tiempo de compilación, en lugar de en tiempo de ejecución

    final Class tipoDatos;

    public abstractDataAccess(Class t) {
        this.tipoDatos = t;
    }

    public abstract EntityManager getEntityManger();

    public abstract String getOrdenAsc();

    public void create(T registtro) {
        if (registtro != null) {
            try {
                EntityManager em = getEntityManger();
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

    public List<T> findRange(int first, int page) {
        EntityManager em = null;

        if (first >= 0 && page > 0) {
            try {
                em = getEntityManger();
                if (em != null) {

                    //construir consultas de criterios (criteria queries). Este es un componente clave de JPA para construir consultas dinámicas.
                    CriteriaBuilder cb = em.getCriteriaBuilder();
                    // estructura de la consulta que se va a construir. A partir de este punto,
                    CriteriaQuery cq = cb.createQuery(tipoDatos);
//              Root representa la entidad raíz de la consulta, que es tipoDatos. Esto se utiliza para definir desde qué entidad se realizará la consulta.
                    Root<T> raiz = cq.from(tipoDatos);
                    cq.select(raiz);
                    //ordenar 
                    //cq.orderBy(cb.asc(raiz.get(getOrdenAsc())));

                    TypedQuery q = em.createQuery(cq);
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

    public T findById(Object id) {
        EntityManager em = null;
        if (id != null) {
            try {
                em = getEntityManger();
                if (em != null) {
                    return (T) em.find(tipoDatos, id);
                }
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
            return null;

        }
        return null;
    }

    public T modify(T registro) {
        EntityManager em = null;
        try {
            em = getEntityManger();
            if (registro != null) {
                if (em != null) {
                    return em.merge(registro);
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
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

    public void delete(T registro) {
        if (registro != null) {
            EntityManager em = null;
            try {
                em = getEntityManger();
                if (em != null) {
                    CriteriaBuilder cb = em.getCriteriaBuilder();
                    CriteriaDelete<T> dq = cb.createCriteriaDelete(tipoDatos);
                    Root<T> raiz = dq.from(tipoDatos);
                    dq.where(cb.equal(raiz, registro));
                    em.createQuery(dq).executeUpdate();
                    return;
                }
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }

}
