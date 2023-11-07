/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parqueowebapp.Boundary.Rest;

import com.mycompany.parqueowebapp.control.abstractDataAccess;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbtractRest<T> {
    abstractDataAccess<T> clBean;

    public abstractDataAccess<T> getClBean() {
        return clBean;
    }

    public void setClBean(abstractDataAccess<T> clBean) {
        this.clBean = clBean;
    }
    
    
    public abstract abstractDataAccess<T> getDataAccess();
    
  
    
    @Context
    protected UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> findAll(@DefaultValue("0") @QueryParam(value = "first") int first,
            @DefaultValue("10") @QueryParam(value = "page") int page) {
        abstractDataAccess<T> clBean = getDataAccess();
        return clBean.findRange(first, page);
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") final Integer id) {
         abstractDataAccess<T> clBean = getDataAccess();
        if (id != null) {
            try {
                T entity =clBean.findById(id);
                return Response.status(Response.Status.OK).entity(entity).build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Falta el parámetro 'id'").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(T entity) {
        abstractDataAccess<T> clBean = getDataAccess();
        if (entity != null) {
            try {
                clBean.create(entity);
                return Response.status(Response.Status.CREATED)
                        .header("location", uriInfo.getRequestUri().toString() + "/" + getIdFromEntity(entity))
                        .build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear el recurso: " + e.getMessage()).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Falta el cuerpo de la solicitud").build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T entity) {
        abstractDataAccess<T> clBean = getDataAccess();
        if (entity != null && getIdFromEntity(entity) != null) {
            try {
                clBean.modify(entity);
                return Response.status(Response.Status.NO_CONTENT).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el recurso: " + e.getMessage()).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Falta el parámetro 'id' en el cuerpo de la solicitud").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final Integer id) {
         abstractDataAccess<T> clBean = getDataAccess();
        if (id != null) {
            T entity = clBean.findById(id);
            if (entity != null) {
                try {
                    clBean.delete(entity);
                    return Response.status(Response.Status.NO_CONTENT).build();
                } catch (Exception e) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el recurso: " + e.getMessage()).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Falta el parámetro 'id'").build();
    }

    // Método abstracto para obtener el ID de una entidad específica
    protected abstract Integer getIdFromEntity(T entity);
}
