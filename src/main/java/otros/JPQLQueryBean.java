/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otros;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Named
public class JPQLQueryBean implements Serializable {

    @PersistenceContext(unitName = "com.mycompany_parqueoWebApp_war_1.0-SNAPSHOTPU")
    EntityManager entityManager;

    public void ejecutarConsulta() {

        // Ejemplo de consulta JPQL
        String jpqlQuery = "SELECT a.nombre FROM Area a WHERE a.id_area = :idArea";
        Query query = entityManager.createQuery(jpqlQuery);
        query.setParameter("idArea", 1); // Establece el parámetro según tus necesidades

        // Ejecuta la consulta y obtén los resultados como una lista de objetos
        List<Object> resultados = query.getResultList();

        // Itera sobre los resultados (pueden ser Strings u otros tipos según la consulta)
        for (Object resultado : resultados) {
            System.out.println("Resultado: " + resultado);
        }

        // Cierra el EntityManager
    }
}
