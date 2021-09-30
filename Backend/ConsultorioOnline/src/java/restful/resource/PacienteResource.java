/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
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
import restful.Model.Paciente;
import restful.service.PacienteService;

@Path("pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 *
 * @author diego
 */
public class PacienteResource {
    PacienteService servicio = new PacienteService();
    
    @GET
    public ArrayList<Paciente> getPaciente() throws ClassNotFoundException{
        return servicio.getPacientes();
    }
    
    @GET
    @Path("/{NombrePaciente}")
    public Paciente getPacienteName(
            @PathParam("NombrePaciente") String name) throws ClassNotFoundException{
        return servicio.getPacienteNombre(name);
    }
    
    @POST
    public Paciente addPacientes(String datos) throws ClassNotFoundException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Paciente paciente = gson.fromJson(datos, Paciente.class);
        return servicio.addPaciente(paciente);
    }
    
    @PUT
    public Paciente updatePaciente(String datos) throws ClassNotFoundException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Paciente paciente = gson.fromJson(datos, Paciente.class);
        return servicio.updatePaciente(paciente);
    }
 
    @DELETE
    @Path("/{PacienteId}")
    public String idPacient(
        @PathParam("PacienteId") int id) throws ClassNotFoundException{
        return servicio.idPaciente(id);
    }
    
}