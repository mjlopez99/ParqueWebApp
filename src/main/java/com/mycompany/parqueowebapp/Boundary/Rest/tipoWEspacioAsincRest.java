/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.Rest;

import com.mycompany.parqueowebapp.control.TipoEspacioBean;
import com.mycompany.parqueowebapp.entitys.TipoEspacio;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author mjlopez
 */
@Path("tipo_espacio_async")
public class tipoWEspacioAsincRest {
     @Inject
    TipoEspacioBean teBean;
     @Resource
     ManagedExecutorService mes;
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public CompletableFuture<List<TipoEspacio>> FindRange(
            @DefaultValue("0") @QueryParam(value = "first") int first,
            @DefaultValue("10") @QueryParam(value = "page") int page
    ) {

        return CompletableFuture.supplyAsync(()->teBean.findeRangeSlow(first, page));
    }
    
}
