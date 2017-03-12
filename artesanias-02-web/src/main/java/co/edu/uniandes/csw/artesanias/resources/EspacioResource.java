/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.EspacioDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspacioLogic;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
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
public class EspacioResource {
    
    @Inject private EspacioLogic espacioLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    @POST
    public EspacioDTO createFeria(EspacioEntity entity) {
        return new EspacioDTO(espacioLogic.createEspacio(entity));
    }
    
    @GET
    public List<EspacioDTO> getArtesanos() {
        return listEntity2DTO(espacioLogic.getEspacios());
    }
    
    @GET
    @Path("{id: \\d+}")
    public EspacioDTO getArtesano(@PathParam("id") Long id ) {
        return new EspacioDTO(espacioLogic.getEspacio(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EspacioDTO updateArtesano(@PathParam("id") Long id, EspacioDTO dto) {
        EspacioEntity entity = dto.toEntity();
        entity.setId(id);
        return new EspacioDTO(espacioLogic.updateEspacio(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteArtesano(@PathParam("id") Long id) {
        espacioLogic.deleteEspacio(id);
    }
    
    private List<EspacioDTO> listEntity2DTO(List<EspacioEntity> entities) {
        List<EspacioDTO> rta = new LinkedList<EspacioDTO>();
        for (EspacioEntity entity : entities)
            rta.add(new EspacioDTO(entity));
        return rta;
    }
}
