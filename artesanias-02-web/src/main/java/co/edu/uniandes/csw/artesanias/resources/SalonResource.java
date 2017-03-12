/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ConferenciaDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.SalonDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.SalonLogic;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
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
public class SalonResource {
    
     @Inject private SalonLogic SalonLogic;
    @Context private HttpServletResponse response;
    @QueryParam("salon") private Integer salon;
    
    
    
    private List<SalonDetailDTO> listEntity2DTO(List<SalonEntity> entityList){
        List<SalonDetailDTO> list = new ArrayList<>();
        for (SalonEntity entity : entityList) {
            list.add(new SalonDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<SalonDetailDTO> getSalones() {
        
        return listEntity2DTO(SalonLogic.getSalones());
    }
    
     @GET
    @Path("{id: \\d+}")
    public SalonDetailDTO getSalon(@PathParam("id") Long id) {
        return new SalonDetailDTO(SalonLogic.getSalon(id));
    }
    
    @GET
    @Path(("{id: \\d+}/Conferencias"))
    public List<ConferenciaDTO> getConferenciasFromSalon(@PathParam( "id" ) Long id ){
        
        SalonDetailDTO x=new SalonDetailDTO(SalonLogic.getSalon(id));
        return x.getConferencias();
    }
    
    
    @POST
    public SalonDetailDTO createSalon(SalonDetailDTO dto) {
        return new SalonDetailDTO(SalonLogic.createSalon(dto.toEntity()));
    }
    
    
     @PUT
    @Path("{id: \\d+}")
    public SalonDetailDTO updateSalon(@PathParam("id") Long id, SalonDetailDTO dto) {
        SalonEntity entity = dto.toEntity();
        entity.setId(id);
        return new SalonDetailDTO(SalonLogic.updateSalon(entity));
    }
    
     @DELETE
    @Path("{id: \\d+}")
    public void deleteSalon(@PathParam("id") Long id) {
        SalonLogic.deleteSalon(id);
    }
}