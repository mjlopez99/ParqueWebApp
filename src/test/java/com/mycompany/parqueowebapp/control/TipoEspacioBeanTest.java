/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parqueowebapp.control;

import com.mycompany.parqueowebapp.entitys.TipoEspacio;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

/**
 *
 * @author mjlopez
 */
public class TipoEspacioBeanTest {

    List<TipoEspacio> registros = new ArrayList<>();

    EntityManager emgeneradoExcepcion = new EntityManager() {

        @Override
        public void persist(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T merge(T t) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void remove(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T find(Class<T> type, Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T find(Class<T> type, Object o, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T find(Class<T> type, Object o, LockModeType lmt) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T find(Class<T> type, Object o, LockModeType lmt, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T getReference(Class<T> type, Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void flush() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void setFlushMode(FlushModeType fmt) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public FlushModeType getFlushMode() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void lock(Object o, LockModeType lmt) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void lock(Object o, LockModeType lmt, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void refresh(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void refresh(Object o, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void refresh(Object o, LockModeType lmt) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void refresh(Object o, LockModeType lmt, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void detach(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean contains(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public LockModeType getLockMode(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void setProperty(String string, Object o) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Map<String, Object> getProperties() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Query createQuery(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> TypedQuery<T> createQuery(CriteriaQuery<T> cq) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Query createQuery(CriteriaUpdate cu) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Query createQuery(CriteriaDelete cd) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> TypedQuery<T> createQuery(String string, Class<T> type) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Query createNamedQuery(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> TypedQuery<T> createNamedQuery(String string, Class<T> type) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Query createNativeQuery(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Query createNativeQuery(String string, Class type) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Query createNativeQuery(String string, String string1) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public StoredProcedureQuery createNamedStoredProcedureQuery(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String string, Class... types) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String string, String... strings) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void joinTransaction() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isJoinedToTransaction() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> T unwrap(Class<T> type) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Object getDelegate() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void close() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isOpen() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public EntityTransaction getTransaction() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public EntityManagerFactory getEntityManagerFactory() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public CriteriaBuilder getCriteriaBuilder() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Metamodel getMetamodel() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> EntityGraph<T> createEntityGraph(Class<T> type) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public EntityGraph<?> createEntityGraph(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public EntityGraph<?> getEntityGraph(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> type) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    };

    public TipoEspacioBeanTest() {
        this.registros.add(new TipoEspacio(1));
        this.registros.get(0).setNombre("chepe");
        this.registros.add(new TipoEspacio(2));
        this.registros.add(new TipoEspacio(3));
    }

    /**
     * Test of create method, of class TipoEspacioBean.
     */
    //@Test
    public void testFindRange() {
        System.out.println("testFind");
        int fisrt = 10;
        int pagesize = 10;
        TipoEspacioBean cut = new TipoEspacioBean();
        List<TipoEspacio> resultado;

        EntityManager em = Mockito.mock(EntityManager.class);
        CriteriaBuilder cbmock = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery cqmock = Mockito.mock(CriteriaQuery.class);
        TypedQuery tq = Mockito.mock(TypedQuery.class);

        Mockito.when(tq.getResultList()).thenReturn(registros);
        Mockito.when(cbmock.createQuery(TipoEspacio.class)).thenReturn(cqmock);

        Mockito.when(em.getCriteriaBuilder()).thenReturn(cbmock);
        Mockito.when(em.createQuery(cqmock)).thenReturn(tq);

        System.out.println("findrange valores nulos");
        resultado = cut.findRange(-1, -1);
        assertTrue(resultado.isEmpty());

        System.out.println("findrange em ideal");
        cut.em = em;
        resultado = cut.findRange(fisrt, pagesize);
        assertNotNull(resultado);
        assertTrue(!resultado.isEmpty());
        assertEquals(registros.size(), resultado.size());

        System.out.println("findrange valores nulos");
        resultado = cut.findRange(-1, -1);
        assertTrue(resultado.isEmpty());

        System.out.println("findrange em malo");
        cut.em = this.emgeneradoExcepcion;
        assertThrows(IllegalStateException.class, () -> {
            List<TipoEspacio> resultadoPrueba = cut.findRange(fisrt, pagesize);
        });
//        fail("fallo xd");
    }

    //@Test
    public void testCreate() {
        System.out.println("create");
        TipoEspacio nuevo = new TipoEspacio();
        nuevo.setNombre("tipo1");
        EntityManager mockem = Mockito.mock(EntityManager.class);

        System.out.println("create caso ideal");
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em = mockem;
        cut.create(nuevo);

        System.out.println("create caso nullo");
        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });
        System.out.println("crate em malo");

        cut.em = this.emgeneradoExcepcion;
        assertThrows(IllegalStateException.class, () -> {
            cut.create(nuevo);
        });
    }

    //@Test
    public void testDelete() {
        EntityManager emMock = Mockito.mock(EntityManager.class);
        TipoEspacio registro = new TipoEspacio();
        registro.setNombre("tipo1");
        TipoEspacio registroborrado;
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em=emMock;
        
        CriteriaBuilder cbmock = Mockito.mock(CriteriaBuilder.class);
        CriteriaDelete cdmock = Mockito.mock(CriteriaDelete.class);
        Root<TipoEspacio> root = Mockito.mock(Root.class);
        
//        Mockito.when(emMock.getCriteriaBuilder()).thenReturn(cbmock);
//        Mockito.when(cbmock.createCriteriaDelete(TipoEspacio.class)).thenReturn(cdmock);
//        Mockito.when(cdmock.from(TipoEspacio.class)).thenReturn(root);
////        Mockito.when(cbmock.equal(root, registro)).thenReturn(registroborrado);
////        System.out.println("falla");
////        Mockito.when(emMock.createQuery(cdmock).executeUpdate()).thenReturn();
//        System.out.println("delete caso ideal");
//        cut.delete(registro);
//        
        System.out.println("delete caso registro nullo");
        assertThrows(IllegalArgumentException.class, ()->{
            cut.delete(null);
        });
//        System.out.println("delete em malo");
//        cut.em=this.emgeneradoExcepcion;
//        assertThrows(IllegalArgumentException.class, ()->{
//            cut.delete(registro);
//        });

    }

    /**
     * Test of getEntity method, of class TipoEspacioBean.
     */
    //@Test
    public void TestModify() {
        System.out.println("modify");
        EntityManager emMock = Mockito.mock(EntityManager.class);
        TipoEspacio registro = new TipoEspacio();
        registro.setNombre("tipo1");
        TipoEspacio registronuevo;
        TipoEspacioBean cut = new TipoEspacioBean();
        cut.em = emMock;

        System.out.println("modify caso ideal");
        Mockito.when(emMock.merge(registro)).thenReturn(registros.get(0));
        registronuevo = cut.modify(registro);
        assertTrue(registronuevo != null);
        assertTrue(registronuevo == registros.get(0));
        
        System.out.println("modify registro nullo");
        registronuevo = cut.modify(null);
        assertTrue(registronuevo == null);
        
        System.out.println("modify caso em malo");
        cut.em=this.emgeneradoExcepcion;
        registronuevo = cut.modify(registro);
        assertTrue(registronuevo == null);
        

    }

    //@Test

    public void testFindById() {
        System.out.println("testFindById");
        EntityManager emMock = Mockito.mock(EntityManager.class);
        TipoEspacio busqueda;
        int id = 1;
        TipoEspacioBean cut = new TipoEspacioBean();
        System.out.println("finfById registro nulo");
        busqueda = cut.findById(null);
        assertTrue(busqueda == null);

        System.out.println("finfById em malo");

        cut.em = this.emgeneradoExcepcion;
        busqueda = cut.findById(id);
        assertTrue(busqueda == null);

        System.out.println("finfById caso ideal");
        cut.em = emMock;
        Mockito.when(emMock.find(TipoEspacio.class, id)).thenReturn(registros.get(id));
        busqueda = cut.findById(id);
        assertTrue(busqueda == registros.get(id));

    }

    /**
     * Test of findRange method, of class TipoEspacioBean.
     */
}
