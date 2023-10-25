package com.mycompany.parqueowebapp.Boundary.Rest;

import com.mycompany.parqueowebapp.control.TipoEspacioBean;
import com.mycompany.parqueowebapp.entitys.TipoEspacio;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("tipo_espacio")
//sirve para especificar la ruta relativa de un recurso o clase que maneja peticiones HTTP en una aplicación web RESTful.
public class TipoEspacioResource {

    @Inject
    TipoEspacioBean teBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TipoEspacio> FindRange(
            @DefaultValue("0") @QueryParam(value = "first") int first,
            @DefaultValue("10") @QueryParam(value = "page") int page
    ) {

        return teBean.findRange(first, page);
    }

    @GET
    @Path("/{id}")
    public Response findById(
            //           @PathParam a diferencia de @QueryParam este indica que el parámetro idTipoEspacio en el método findById se espera que se extraiga de la ruta (URI)
            //            en lugar de los parámetros de consulta (query parameters).
            @PathParam(value = "id")
            final Integer idTipoEspacio) {
        if (idTipoEspacio != null) {
            TipoEspacio findById = teBean.findById(idTipoEspacio);
            if (findById != null) {
                return Response.status(200)
                        .entity(findById)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).
                        header("not found", idTipoEspacio).
                        build();
            }

        }
        return Response.status(422)
                .header("missing parameter", "id")
                .build();
    }
    @POST
    //@produces sirve para especificar el tipo de medio que un método del servicio web produce. En este caso, indica que el método produce datos en formato JSON.
    @Produces({MediaType.APPLICATION_JSON})
    //@consumes se utiliza para especificar el tipo de medio que un método del servicio web puede consumir. En este caso específico, indica que el método puede aceptar datos en formato JSON.
    @Consumes({MediaType.APPLICATION_JSON})
    public Response Create(TipoEspacio registro,
            @Context
            UriInfo uri
            ) {
        if (registro!=null && registro.getIdTipoEspacio()!=null && registro.getNombre()!=null) {
            try {
                teBean.create(registro);
                return Response.status(Response.Status.CREATED)
                        .header("location", uri.getRequestUri().toString()+"/"+registro.getIdTipoEspacio())
                        .build();
                        
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            return Response.status(500)
                    .header("created-excepcion", registro)
                    .build();
        }
        return Response.status(422).
                header("missing parameter","id").
                build();
    }

}
