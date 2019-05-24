/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citas;

import citas.pojos.Cita;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author sebas
 */
@Path("citas")
public class CitasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CitasResource
     */
    public CitasResource() {
    }

    /**
     * Retrieves representation of an instance of citas.CitasResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("prueba")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllCitas() {
       
        List<Cita> citas=CicloBD.getAllCitas;
        
        return "Funciona";
    }

    /**
     * PUT method for updating or creating an instance of CitasResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
