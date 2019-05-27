/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citas;

import citas.conexion.CitaBD;
import citas.pojos.Cita;
import com.google.gson.Gson;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
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
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCitas() throws Exception { 
        try {
            List<Cita> citas = CitaBD.getAllCitas();
            String json = new Gson().toJson(citas);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.CREATED).entity("Error en la consulta de datos").build();
        }
    }

    //Añadir Cita
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCita(Cita registro){
        try {
          
            CitaBD.addCita(registro);
            Date fecha= new Date();
            System.out.println(fecha+": Se ha añadido - addCita" + registro.getId());
            String json = "{ \"id\": \""+ String.valueOf(registro.getId())+"\" }";
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("No se pudo insertar la cita: "+ registro.getId()).build();
        }
        
        
        
    }
    
    
    
    /**
     * PUT method for updating or creating an instance of CitasResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCita(Cita registro, @PathParam("id") int id) {
        System.out.println("Llegó la actualización");
        try{
            CitaBD.updateCita(registro);
            Date fecha = new Date();
            System.out.println(fecha.toString()+ ": Se ha actualizado - updateCita " + registro.getId());
            String json = "{ \"id\": \""+ String.valueOf(registro.getId())+"\" }";
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            
            
        }catch(Exception ex){
            return Response.status(Response.Status.SEE_OTHER).entity("No se pudo actualizar la cita: "+ registro.getId()).build();
        }
        
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCita(@PathParam("id") int id){
        
        try {
            CitaBD.deleteCita(id);
            Date fecha=new Date();
            System.out.println(fecha.toString()+": Se ha borrado - deleteCita" + id);
            String json ="{ \"id\""+ id + "\"}";
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("No se pudo borrar la cita "+ id).build(); 
        }
        
    }
    
    
}
