/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TSET {

    private EntityManager em;

    @BeforeEach
    public void setUp() {
        // Configura la unidad de persistencia (persistence unit)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU");
        // Crea un EntityManager
        em = emf.createEntityManager();
    }

    @Test
    public void pruebas() {
        System.out.println(em != null);
    }
}
