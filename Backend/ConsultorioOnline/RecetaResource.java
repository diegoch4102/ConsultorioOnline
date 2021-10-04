/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restful.Model.RecetaModel;
import restful.service.RecetaService;

/**
 *
 * @author JOSE
 */
@Path("recetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class RecetaResource {
    
    RecetaService servicio = new RecetaService();
    
     @GET//consulta todas las recetas
    public ArrayList<RecetaModel> getReceta() {
        
        return servicio.getReceta();
    }

     
  @Path("/{id}")
    @GET//consulta una sola receta
    public RecetaModel getCliente(@PathParam("id") int id) {

        return servicio.getReceta(id);
    }

    @POST//para agregar una nueva receta 
    public RecetaModel addReceta(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        RecetaModel receta = gson.fromJson(JSON, RecetaModel.class);
        return servicio.addReceta(receta);
    }

    @DELETE//elimina un registro sobre la receta
    @Path("/{id}")
    public String delReceta(@PathParam("id") int id) {

        return servicio.delReceta(id);

    }

    @PUT//modifica los campos de la receta 
    public RecetaModel updateReceta(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        RecetaModel receta = gson.fromJson(JSON, RecetaModel.class);
        return servicio.updateReceta(receta);
    }

}