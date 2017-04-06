/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.detail.BoletaDetailDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.ConferenciaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.FeriaLogic;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Miller
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FeriaConferenciaResource {
    
    @Inject private FeriaLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos dependiendo de las conferencias
    //--------------------------------------------------------------------------
    
    @GET
    @Path("{id: \\d+}")
    public ConferenciaDetailDTO getFeriaBoleta(@PathParam("idFeria") Long idFeria,
            @PathParam("id") Long id) {
        if (logic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        return new ConferenciaDetailDTO(logic.getConferencia(idFeria, id));
    }
    
    @GET
    public List<ConferenciaDetailDTO> getFeriaBoletas(@PathParam("idFeria") Long idFeria) {
        if (logic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        return listConferenciaEntity2DetailDTO(logic.getConferencias(idFeria));
    }
    
    @PUT
    public List<ConferenciaDetailDTO> updateFeriaBoleta(@PathParam("idFeria") Long idFeria,
            List<ConferenciaDetailDTO> boletas) {
        if (logic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        for (ConferenciaDetailDTO o : boletas) {
            logic.addBoleta(idFeria, o.getId());
        }
        return listConferenciaEntity2DetailDTO(logic.getFeria(idFeria).getConferencias());
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFeriaBoleta(@PathParam("idFeria") Long idFeria,
            @PathParam("id") Long id) {
        if (logic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        logic.removeConferencia(idFeria, id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<ConferenciaDetailDTO> listConferenciaEntity2DetailDTO(List<ConferenciaEntity> entities) {
        List<ConferenciaDetailDTO> rta = new LinkedList<>();
        for (ConferenciaEntity entity : entities)
            rta.add(new ConferenciaDetailDTO(entity));
        return rta;
    }
    
}
