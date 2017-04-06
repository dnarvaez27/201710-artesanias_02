/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.detail.ArtesanoDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.CiudadLogic;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
public class CiudadArtesanoResource {
    
    @Inject private CiudadLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos dependiendo de los espacios
    //--------------------------------------------------------------------------
    
    @GET
    @Path("{id: \\d+}")
    public ArtesanoDetailDTO getCiudadArtesano(@PathParam("idCiudad") Long idCiudad,
            @PathParam("id") Long id) {
        if (logic.getCiudad(idCiudad) == null)
            throw new WebApplicationException("No existe la ciudad", 404);
        return new ArtesanoDetailDTO(logic.getArtesano(id, idCiudad));
    }
    
    @GET
    public List<ArtesanoDetailDTO> getCiudadArtesanos(@PathParam("idCiudad") Long idCiudad) {
        if (logic.getCiudad(idCiudad) == null)
            throw new WebApplicationException("No existe la ciudad", 404);
        return listArtesanoEntity2DetailDTO(logic.getArtesanos(idCiudad));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudadArtesano(@PathParam("idCiudad") Long idCiudad,
            @PathParam("id") Long id) throws BusinessLogicException {
        if (logic.getCiudad(idCiudad) == null)
            throw new WebApplicationException("No existe la ciudad", 404);
        logic.removeArtesano(id, idCiudad);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<ArtesanoDetailDTO> listArtesanoEntity2DetailDTO(List<ArtesanoEntity> entities) {
        List<ArtesanoDetailDTO> rta = new LinkedList<>();
        for (ArtesanoEntity entity : entities)
            rta.add(new ArtesanoDetailDTO(entity));
        return rta;
    }
    
}
