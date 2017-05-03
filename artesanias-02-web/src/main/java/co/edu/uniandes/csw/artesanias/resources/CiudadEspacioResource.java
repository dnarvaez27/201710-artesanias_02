/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.detail.EspacioDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.CiudadLogic;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
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
public class CiudadEspacioResource {
    
    @Inject private CiudadLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos dependiendo de los espacios
    //--------------------------------------------------------------------------
    
    @GET
    @Path("{id: \\d+}")
    public EspacioDetailDTO getCiudadEspacio(@PathParam("idCiudad") Long idCiudad,
            @PathParam("id") Long id) {
        if (logic.getCiudad(idCiudad) == null)
            throw new WebApplicationException("No existe la ciudad", 404);
        return new EspacioDetailDTO(logic.getEspacio(idCiudad, id));
    }
    
    @GET
    public List<EspacioDetailDTO> getCiudadEspacios(@PathParam("idCiudad") Long idCiudad) {
        if (logic.getCiudad(idCiudad) == null)
            throw new WebApplicationException("No existe la ciudad", 404);
        return listEspacioEntity2DetailDTO(logic.getEspacios(idCiudad));
    }
    
    @PUT
    public List<EspacioDetailDTO> updateCiudadEspacios(@PathParam("idCiudad") Long idCiudad,
            List<EspacioDetailDTO> espacios) {
        if (logic.getCiudad(idCiudad) == null)
            throw new WebApplicationException("No existe la ciudad", 404);
        for (EspacioDetailDTO o : espacios) {
            logic.addEspacio(o.getId(), idCiudad);
        }
        return listEspacioEntity2DetailDTO(logic.getCiudad(idCiudad).getEspacios());
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudadEspacios(@PathParam("idCiudad") Long idCiudad,
            @PathParam("id") Long id) {
        if (logic.getCiudad(idCiudad) == null)
            throw new WebApplicationException("No existe la ciudad", 404);
        logic.removeEspacio(id, idCiudad);
    }
    
    @Path("{id: \\d+}/ferias")
    public Class<EspacioFeriaResource> getEspacioFeriaResource() {
        return EspacioFeriaResource.class;
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<EspacioDetailDTO> listEspacioEntity2DetailDTO(List<EspacioEntity> entities) {
        List<EspacioDetailDTO> rta = new LinkedList<>();
        for (EspacioEntity entity : entities)
            rta.add(new EspacioDetailDTO(entity));
        return rta;
    }
}
