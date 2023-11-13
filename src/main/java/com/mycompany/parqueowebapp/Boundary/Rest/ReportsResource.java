
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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                parametros.put("firma", "nayik bukele");
            }
            case "area" -> {
//                URL urlReporte=getClass().getResource("/reportes/prn32023/tipoEspacio.jasper");
                pathReporte="/reportes/prn32023/area.jasper";
                URL PATH_SUBREPORT=ReportsResource.class.getResource(pathReporte);
                String PATH_SUBREPORT_STRING=PATH_SUBREPORT.getPath();
                System.out.println(PATH_SUBREPORT_STRING);
                parametros.put("PATH_SUBPARAMETER", PATH_SUBREPORT_STRING.substring(0, PATH_SUBREPORT_STRING.lastIndexOf("/"))+"/");
                System.out.println(PATH_SUBREPORT_STRING.substring(0, PATH_SUBREPORT_STRING.lastIndexOf("/"))+"/");
            }
            default -> {
                return Response.status(Response.Status.NOT_FOUND).header("report-not-found", nombreReporte).entity(Entity.text("no se encuentra el reporte")).build();
            }
        }
        if (pathReporte!=null) {
            try {
                //primer paso se debe llenar el reporte
                InputStream fuenteReporte=ReportsResource.class.getResourceAsStream(pathReporte);
                JasperPrint impresor = JasperFillManager.fillReport(fuenteReporte, parametros, poolConneciones.getConnection());
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
                System.out.println("pasa");
                return Response.ok(steam,"application/pdf").build();
            } catch (Exception ex) {
                Logger.getLogger(ReportsResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return Response.serverError().build();
    }
}
