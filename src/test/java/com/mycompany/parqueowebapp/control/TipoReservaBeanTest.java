/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mjlopez
 */
public class TipoReservaBeanTest {
    
    public TipoReservaBeanTest() {
    }

    /**
     * Test of getEntityManger method, of class TipoReservaBean.
     */
//    @Test
    public void testGetEntityManger() {
        System.out.println("getEntityManger");
        TipoReservaBean instance = new TipoReservaBean();
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManger();
        assertEquals(expResult, result);
       // fail("The test case is a prototype.");
    }
    
}
