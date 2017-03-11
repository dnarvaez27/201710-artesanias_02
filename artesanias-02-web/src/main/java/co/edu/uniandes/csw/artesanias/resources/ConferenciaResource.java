/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;


import co.edu.uniandes.csw.artesanias.dtos.detail.ConferenciaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ConferenciaLogic;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import java.util.ArrayList;
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
 * @author IVAN
 */
@Path("/salon")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConferenciaResource {
    
    

    
     @Inject private ConferenciaLogic conferenciaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("tema") private Integer tema;
    
    
    
    private List<ConferenciaDetailDTO> listEntity2DTO(List<ConferenciaEntity> entityList){
        List<ConferenciaDetailDTO> list = new ArrayList<>();
        for (ConferenciaEntity entity : entityList) {
            list.add(new ConferenciaDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<ConferenciaDetailDTO> getConferencias() {
        
        return listEntity2DTO(conferenciaLogic.getConferencias());
    }
    
     @GET
    @Path("{id: \\d+}")
    public ConferenciaDetailDTO getConferencia(@PathParam("id") Long id) {
        return new ConferenciaDetailDTO(conferenciaLogic.getConferencia(id));
    }
    
    @POST
    public ConferenciaDetailDTO createConferencia(ConferenciaDetailDTO dto) {
        return new ConferenciaDetailDTO(conferenciaLogic.createConferencia(dto.toEntity()));
    }
    
    
     @PUT
    @Path("{id: \\d+}")
    public ConferenciaDetailDTO updateConferencia(@PathParam("id") Long id, ConferenciaDetailDTO dto) {
        ConferenciaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ConferenciaDetailDTO(conferenciaLogic.updateConferencia(entity));
    }
    
     @DELETE
    @Path("{id: \\d+}")
    public void deleteSConferencia(@PathParam("id") Long id) {
        conferenciaLogic.deleteConferencia(id);
    }
}