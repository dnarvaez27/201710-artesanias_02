/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.FeriaDTO;
import co.edu.uniandes.csw.artesanias.ejbs.FeriaLogic;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
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
public class FeriaResource {
    
    @Inject private FeriaLogic feriaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    @POST
    public FeriaDTO createFeria(FeriaEntity entity) {
        return new FeriaDTO(feriaLogic.createFeria(entity));
    }
    
    @GET
    public List<FeriaDTO> getArtesanos() {
        return listEntity2DTO(feriaLogic.getFerias());
    }
    
    @GET
    @Path("{id: \\d+}")
    public FeriaDTO getArtesano(@PathParam("id") Long id ) {
        return new FeriaDTO(feriaLogic.getFeria(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public FeriaDTO updateArtesano(@PathParam("id") Long id, FeriaDTO dto) {
        FeriaEntity entity = dto.toEntity();
        entity.setId(id);
        return new FeriaDTO(feriaLogic.updateFeria(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteArtesano(@PathParam( "id" ) Long id ) {
        feriaLogic.deleteFeria(id);
    }
    
    private List<FeriaDTO> listEntity2DTO(List<FeriaEntity> entities) {
        List<FeriaDTO> rta = new LinkedList<FeriaDTO>();
        for (FeriaEntity entity : entities)
            rta.add(new FeriaDTO(entity));
        return rta;
    }
}
