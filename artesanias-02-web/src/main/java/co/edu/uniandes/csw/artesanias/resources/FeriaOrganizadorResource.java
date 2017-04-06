/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.FeriaDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.EspacioDetailDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.FeriaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.FeriaLogic;
import co.edu.uniandes.csw.artesanias.ejbs.OrganizadorLogic;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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
public class FeriaOrganizadorResource {
    
    @Inject private FeriaLogic feriaLogic;
    @Inject private OrganizadorLogic organizadorLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos dependiendo de los organizadores
    //--------------------------------------------------------------------------
    
    @GET
    public List<FeriaDTO> getFeriasO(@PathParam("idOrganizador") Long idOrganizador)
            throws BusinessLogicException {
        if (organizadorLogic.getOrganizador(idOrganizador) == null)
            throw new WebApplicationException("El organizador no existe", 404);
        return listEntity2DTO(feriaLogic.getFeriasO(idOrganizador));
    }
    
    @GET
    @Path("{id: \\d+}")
    public FeriaDetailDTO getFeriaO(@PathParam("idOrganizador") Long idOrganizador, 
            @PathParam("id") Long id) throws BusinessLogicException {
        if (organizadorLogic.getOrganizador(idOrganizador) == null)
            throw new WebApplicationException("El organizador no existe", 404);
        if (feriaLogic.getFeriaO(idOrganizador, id) == null)
            throw new WebApplicationException("No existe la feria", 404);
        return new FeriaDetailDTO(feriaLogic.getFeria(id));
    }
    
    @GET
    @Path("{id: \\d+}/espacios")
    public EspacioDetailDTO getEspacio(@PathParam("idOrganizador") Long idOrganizador, 
            @PathParam("id") Long id) throws BusinessLogicException {
        if (organizadorLogic.getOrganizador(idOrganizador) == null)
            throw new WebApplicationException("El organizador no existe", 404);
        FeriaEntity fe = feriaLogic.getFeriaO(idOrganizador, id);
        if (fe == null)
            throw new WebApplicationException("No existe la feria", 404);
        return new EspacioDetailDTO(fe.getEspacio());
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<FeriaDTO> listEntity2DTO(List<FeriaEntity> entities) {
        List<FeriaDTO> rta = new LinkedList<FeriaDTO>();
        for (FeriaEntity entity : entities)
            rta.add(new FeriaDTO(entity));
        return rta;
    }
    
}
