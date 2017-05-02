/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.detail.BoletaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.FeriaLogic;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
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
public class FeriaBoletaResource {
    
    @Inject private FeriaLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

    public FeriaBoletaResource(FeriaLogic logic) {
        this.logic = logic;
    }
    
    //--------------------------------------------------------------------------
    // Métodos dependiendo de las boletas
    //--------------------------------------------------------------------------
    
    @GET
    @Path("{id: \\d+}")
    public BoletaDetailDTO getFeriaBoleta(@PathParam("idFeria") Long idFeria,
            @PathParam("id") Long id) {
        if (logic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        return new BoletaDetailDTO(logic.getBoleta(idFeria, id));
    }
    
    @GET
    public List<BoletaDetailDTO> getFeriaBoletas(@PathParam("idFeria") Long idFeria) {
        if (logic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        return listBoletaEntity2DetailDTO(logic.getBoletas(idFeria));
    }
    
    @PUT
    public List<BoletaDetailDTO> updateFeriaBoleta(@PathParam("idFeria") Long idFeria,
            List<BoletaDetailDTO> boletas) {
        if (logic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        for (BoletaDetailDTO o : boletas) {
            logic.addBoleta(idFeria, o.getId());
        }
        return listBoletaEntity2DetailDTO(logic.getFeria(idFeria).getBoletas());
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFeriaBoleta(@PathParam("idFeria") Long idFeria,
            @PathParam("id") Long id) {
        if (logic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        logic.removeBoleta(idFeria, id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<BoletaDetailDTO> listBoletaEntity2DetailDTO(List<BoletaEntity> entities) {
        List<BoletaDetailDTO> rta = new LinkedList<>();
        for (BoletaEntity entity : entities)
            rta.add(new BoletaDetailDTO(entity));
        return rta;
    }
}
