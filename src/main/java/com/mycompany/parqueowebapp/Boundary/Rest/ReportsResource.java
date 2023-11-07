
package com.mycompany.parqueowebapp.Boundary.Rest;

import javax.sql.DataSource;
import jakarta.annotation.Resource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Path("reporte")

public class ReportsResource implements Serializable{
    @Resource(lookup = "jdbc/pgdb")
    DataSource poolConneciones;
    @GET
    @Path("{reporte}")
    public Response generarReporte(@PathParam("reporte") final String nombreReporte){
        String pathReporte=null;
        HashMap parametros=new HashMap();
        switch (nombreReporte) {
            case "tipo_espacio" -> {
//                URL urlReporte=getClass().getResource("/reportes/prn32023/tipoEspacio.jasper");
                pathReporte="/reportes/prn32023/tipoEspacio.jasper";
            }
            default -> {
                return Response.status(Response.Status.NOT_FOUND).header("report-not-found", nombreReporte).entity(Entity.text("no se encuentra el reporte")).build();
            }
        }
        if (pathReporte!=null) {
            try {
                //primer paso se debe llenar el reporte
                InputStream fuenteReporte=ReportsResource.class.getResourceAsStream(pathReporte);
                JasperPrint impresor = JasperFillManager.fillReport(pathReporte, parametros, poolConneciones.getConnection());
                SimplePdfExporterConfiguration configuration=new SimplePdfExporterConfiguration();
                configuration.setMetadataAuthor("Universida de El Salvador");
                configuration.setMetadataTitle("Sistema de reporte de Universida de El Salvador");
                //exportamos a un stream
                JRPdfExporter exportador= new JRPdfExporter();
                exportador.setConfiguration(configuration);
                exportador.setExporterInput(new SimpleExporterInput(impresor));
                StreamingOutput steam=new StreamingOutput() {
                    @Override
                    public void write(OutputStream out) throws IOException, WebApplicationException {
                        exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                        try {
                            exportador.exportReport();
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                    }
                };
                
                return Response.ok(steam,"application/pdf").build();
            } catch (Exception ex) {
                Logger.getLogger(ReportsResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return Response.serverError().build();
    }
}
