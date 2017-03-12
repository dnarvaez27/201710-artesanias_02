/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.BoletaDTO;
import co.edu.uniandes.csw.artesanias.ejbs.BoletaLogic;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Miller
 */
@Path("/ferias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BoletaResource {
    
    @Inject private BoletaLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    @POST
    public BoletaDTO createBoleta(BoletaEntity entity) {
        return new BoletaDTO(logic.createBoleta(entity));
    }
    
    @GET
    public List<BoletaDTO> getBoletas() {
        return listEntity2DTO(logic.getBoletas());
    }
    
    @GET
    @Path("{id: \\d+}")
    public BoletaDTO getBoleta(@PathParam("id") Long id ) {
        return new BoletaDTO(logic.getBoleta(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public BoletaDTO updateBoleta(@PathParam("id") Long id, BoletaDTO dto) {
        BoletaEntity entity = dto.toEntity();
        entity.setId(id);
        return new BoletaDTO(logic.updateBoleta(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBoleta(@PathParam("id") Long id) {
        logic.deleteBoleta(id);
    }
    
    private List<BoletaDTO> listEntity2DTO(List<BoletaEntity> entities) {
        List<BoletaDTO> rta = new LinkedList<BoletaDTO>();
        for (BoletaEntity entity : entities)
            rta.add(new BoletaDTO(entity));
        return rta;
    }
}
