/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.detail.FeriaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspacioLogic;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
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
public class EspacioFeriaResource {
    
    @Inject private EspacioLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos dependiendo de las ferias
    //--------------------------------------------------------------------------
    
    @GET
    @Path("{id: \\d+}")
    public FeriaDetailDTO getCiudadEspacio(@PathParam("idEspacio") Long idEspacio,
            @PathParam("id") Long id) {
        if (logic.getEspacio(idEspacio) == null)
            throw new WebApplicationException("No existe el espacio", 404);
        return new FeriaDetailDTO(logic.getFeria(id, idEspacio));
    }
    
    @GET
    public List<FeriaDetailDTO> getCiudadEspacios(@PathParam("idEspacio") Long idEspacio) {
        if (logic.getEspacio(idEspacio) == null)
            throw new WebApplicationException("No existe el espacio", 404);
        return listFeriaEntity2DetailDTO(logic.getFerias(idEspacio));
    }
    
    @PUT
    public List<FeriaDetailDTO> updateCiudadEspacios(@PathParam("idEspacio") Long idEspacio,
            List<FeriaDetailDTO> ferias) {
        if (logic.getEspacio(idEspacio) == null)
            throw new WebApplicationException("No existe el espacio", 404);
        for (FeriaDetailDTO o : ferias) {
            logic.addFeria(o.getId(), idEspacio);
        }
        return listFeriaEntity2DetailDTO(logic.getEspacio(idEspacio).getFerias());
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudadEspacios(@PathParam("idEspacio") Long idEspacio,
            @PathParam("id") Long id) {
        if (logic.getEspacio(idEspacio) == null)
            throw new WebApplicationException("No existe el espacio", 404);
        logic.removeFeria(id, idEspacio);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<FeriaDetailDTO> listFeriaEntity2DetailDTO(List<FeriaEntity> entities) {
        List<FeriaDetailDTO> rta = new LinkedList<>();
        for (FeriaEntity entity : entities)
            rta.add(new FeriaDetailDTO(entity));
        return rta;
    }
    
}
