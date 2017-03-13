/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.CiudadDTO;
import co.edu.uniandes.csw.artesanias.ejbs.CiudadLogic;
import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
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
@Path("/ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadResource {
    
    @Inject private CiudadLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    @POST
    public CiudadDTO createFeria(CiudadEntity entity) {
        return new CiudadDTO(logic.createCiudad(entity));
    }
    
    @GET
    public List<CiudadDTO> getArtesanos() {
        return listEntity2DTO(logic.getCiudades());
    }
    
    @GET
    @Path("{id: \\d+}")
    public CiudadDTO getArtesano(@PathParam("id") Long id ) {
        return new CiudadDTO(logic.getCiudad(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CiudadDTO updateArtesano(@PathParam("id") Long id, CiudadDTO dto) {
        CiudadEntity entity = dto.toEntity();
        entity.setId(id);
        return new CiudadDTO(logic.updateCiudad(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteArtesano(@PathParam("id") Long id) {
        logic.deleteCiudad(id);
    }
    
    private List<CiudadDTO> listEntity2DTO(List<CiudadEntity> entities) {
        List<CiudadDTO> rta = new LinkedList<CiudadDTO>();
        for (CiudadEntity entity : entities)
            rta.add(new CiudadDTO(entity));
        return rta;
    }
}