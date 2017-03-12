/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.ejbs.PabellonLogic;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
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
 * @author ja.espinosa12
 */

@Path("/pabellones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PabellonResource {
    
    @Inject private PabellonLogic logic;
    
    @Context HttpServletResponse response;
    
    @QueryParam("page")
    private Integer page;
    
    @QueryParam("limit")
    private Integer maxRec;
    
    @POST
    public PabellonDTO createPabellon(PabellonEntity entity )
    {
        return new PabellonDTO(logic.createPabellon(entity));
    }
    
    @GET
    public List<PabellonDTO> getPabellones()
    {
        return listEntity2DTO(logic.getPabellones());
    }
    
    @GET
    @Path("{id: \\d+}")
    public PabellonDTO getPabellon(@PathParam("id") Long id ) {
        return new PabellonDTO(logic.getPabellon(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public PabellonDTO updatePabellon(@PathParam("id") Long id, PabellonDTO dto) {
        PabellonEntity entity = dto.toEntity();
        entity.setId(id);
        return new PabellonDTO(logic.updatePabellon(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deletePabellon(@PathParam( "id" ) Long id ) {
        logic.deletePabellon(id);
    }
    
    private List<PabellonDTO> listEntity2DTO(List<PabellonEntity> entities) {
        List<PabellonDTO> rta = new LinkedList<PabellonDTO>();
        for (PabellonEntity entity : entities)
            rta.add(new PabellonDTO(entity));
        return rta;
    }
}
